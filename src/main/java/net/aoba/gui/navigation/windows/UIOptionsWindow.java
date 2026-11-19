/*
 * ShellShock Client
 * Copyright (C) 2019-2024 coltonk9043
 *
 * Licensed under the GNU General Public License, Version 3 or later.
 * See <http://www.gnu.org/licenses/>.
 */

package net.aoba.gui.navigation.windows;

import net.aoba.Aoba;
import net.aoba.gui.GuiManager;
import net.aoba.gui.UIElement;
import net.aoba.gui.components.CheckboxComponent;
import net.aoba.gui.components.KeybindComponent;
import net.aoba.gui.components.StackPanelComponent;
import net.aoba.gui.components.StringComponent;
import net.aoba.gui.font.FontManager;
import net.aoba.gui.navigation.Window;
import net.aoba.gui.types.BindingMode;
import net.aoba.gui.types.SizeToContent;

public class UIOptionsWindow extends Window {
	public UIOptionsWindow() {
		super("UI Settings", 600, 200);
		sizeToContent = SizeToContent.Both;
		setProperty(UIElement.MinWidthProperty, 400.0f);

		StackPanelComponent stackPanel = new StackPanelComponent();
		stackPanel.setSpacing(8f);

		StringComponent keybindsHeader = new StringComponent("Keybinds");
		keybindsHeader.setProperty(UIElement.FontWeightProperty, FontManager.WEIGHT_BOLD);
		keybindsHeader.bindProperty(UIElement.ForegroundProperty, GuiManager.foregroundHeaderColor);
		stackPanel.addChild(keybindsHeader);

		KeybindComponent clickGuiButtonComponent = new KeybindComponent();
		clickGuiButtonComponent.bindProperty(KeybindComponent.SelectedKeyProperty, Aoba.getInstance().guiManager.clickGuiButton, BindingMode.TwoWay);
		clickGuiButtonComponent.setProperty(KeybindComponent.HeaderProperty, Aoba.getInstance().guiManager.clickGuiButton.displayName);
		stackPanel.addChild(clickGuiButtonComponent);

		CheckboxComponent tooltipCheckbox = new CheckboxComponent();
		tooltipCheckbox.setProperty(CheckboxComponent.HeaderProperty, GuiManager.enableTooltips.displayName);
		tooltipCheckbox.bindProperty(CheckboxComponent.IsCheckedProperty, GuiManager.enableTooltips, BindingMode.TwoWay);
		stackPanel.addChild(tooltipCheckbox);

		setContent(stackPanel);
	}
}
