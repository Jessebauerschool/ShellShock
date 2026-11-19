/*
 * ShellShock Client
 * Copyright (C) 2019-2024 coltonk9043
 *
 * Licensed under the GNU General Public License, Version 3 or later.
 * See <http://www.gnu.org/licenses/>.
 */

package net.aoba.gui.screens.proxy;

import java.util.ArrayList;

import net.aoba.Aoba;
import net.aoba.gui.components.widgets.AobaButtonWidget;
import net.aoba.gui.screens.AobaPanorama;
import net.aoba.managers.proxymanager.Socks5Proxy;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ProxyScreen extends Screen {
	protected static final AobaPanorama AOBA_ROTATING_PANORAMA_RENDERER = new AobaPanorama();

	private final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this);
	private final Screen parentScreen;
	private ProxySelectionList proxyListSelector;
	private AobaButtonWidget editButton;
	private AobaButtonWidget deleteButton;

	public ProxyScreen(Screen parentScreen) {
		super(Component.nullToEmpty("Proxy Manager"));
		this.parentScreen = parentScreen;
	}

	public void init() {
		super.init();

		proxyListSelector = new ProxySelectionList(this, minecraft, width, height, 32, 52);
		proxyListSelector.updateProxies();
		layout.addToContents(proxyListSelector);

		LinearLayout buttonRow = LinearLayout.horizontal().spacing(4);
		buttonRow.addChild(new AobaButtonWidget(0, 0, 100, 20, Component.nullToEmpty("Add Proxy"),
				b -> minecraft.gui.setScreen(new AddProxyScreen(this))));

		editButton = new AobaButtonWidget(0, 0, 100, 20, Component.nullToEmpty("Edit Proxy"),
				b -> editSelected());
		editButton.active = false;
		buttonRow.addChild(editButton);

		deleteButton = new AobaButtonWidget(0, 0, 100, 20, Component.nullToEmpty("Delete Proxy"),
				b -> deleteSelected());
		deleteButton.active = false;
		buttonRow.addChild(deleteButton);

		buttonRow.addChild(new AobaButtonWidget(0, 0, 100, 20, Component.nullToEmpty("Cancel"),
				b -> minecraft.gui.setScreen(parentScreen)));

		layout.addToFooter(buttonRow);
		layout.arrangeElements();
		layout.visitWidgets(this::addRenderableWidget);
		proxyListSelector.updateSize(width, layout);
	}

	@Override
	protected void repositionElements() {
		layout.arrangeElements();
		if (proxyListSelector != null) {
			proxyListSelector.updateSize(width, layout);
		}
	}

	public ArrayList<Socks5Proxy> getProxyList() {
		return Aoba.getInstance().proxyManager.getProxies();
	}

	public void refreshProxyList() {
		minecraft.gui.setScreen(new ProxyScreen(parentScreen));
	}

	public void setSelected(ProxySelectionList.Entry selected) {
		proxyListSelector.setSelected(selected);
		setEdittable();
	}

	public void editSelected() {
		Socks5Proxy proxy = ((ProxySelectionList.NormalEntry) proxyListSelector.getSelected()).getProxyData();
		if (proxy == null) {
			return;
		}
		minecraft.gui.setScreen(new EditProxyScreen(this, proxy));
	}

	public void deleteSelected() {
		Socks5Proxy proxy = ((ProxySelectionList.NormalEntry) proxyListSelector.getSelected()).getProxyData();
		if (proxy == null) {
			return;
		}
		Aoba.getInstance().proxyManager.removeProxy(proxy);
		refreshProxyList();
	}

	protected void setEdittable() {
		editButton.active = true;
		deleteButton.active = true;
	}

	public boolean isActiveProxy(Socks5Proxy proxy) {
		return Aoba.getInstance().proxyManager.getActiveProxy() == proxy;
	}

	public void setActive() {
		ProxySelectionList.Entry proxyListSelectorSelectedOrNull = proxyListSelector.getSelected();

		if (proxyListSelectorSelectedOrNull == null) {
			return;
		}

		Socks5Proxy proxy = ((ProxySelectionList.NormalEntry) proxyListSelectorSelectedOrNull).getProxyData();
		Aoba.getInstance().proxyManager.setActiveProxy(proxy);
	}

	public void resetActive() {
		Aoba.getInstance().proxyManager.setActiveProxy(null);
	}

	@Override
	protected void extractPanorama(final GuiGraphicsExtractor graphics, final float a){
		try {
			AOBA_ROTATING_PANORAMA_RENDERER.extractRenderState(graphics, this.width, this.height, true);
		} catch (IllegalStateException e) {
		}
	}
}
