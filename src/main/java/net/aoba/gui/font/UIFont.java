/*
 * ShellShock Client
 * Copyright (C) 2019-2024 coltonk9043
 *
 * Licensed under the GNU General Public License, Version 3 or later.
 * See <http://www.gnu.org/licenses/>.
 */

package net.aoba.gui.font;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;

public class UIFont {
	private final String name;

	public UIFont(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Font getRenderer() {
		return Minecraft.getInstance().font;
	}

	public Font getRenderer(float fontSize, int weight) {
		return getRenderer();
	}
}
