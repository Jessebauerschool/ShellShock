/*
 * ShellShock Client
 * Copyright (C) 2019-2024 coltonk9043
 *
 * Licensed under the GNU General Public License, Version 3 or later.
 * See <http://www.gnu.org/licenses/>.
 */

package net.aoba.gui.font;

public class FontManager {
	public static final String DEFAULT_FONT = "Minecraft";

	public static final int WEIGHT_THIN = 100;
	public static final int WEIGHT_EXTRA_LIGHT = 200;
	public static final int WEIGHT_LIGHT = 300;
	public static final int WEIGHT_NORMAL = 400;
	public static final int WEIGHT_MEDIUM = 500;
	public static final int WEIGHT_SEMI_BOLD = 600;
	public static final int WEIGHT_BOLD = 700;
	public static final int WEIGHT_EXTRA_BOLD = 800;
	public static final int WEIGHT_BLACK = 900;

	public static final float DEFAULT_FONT_SIZE = 6f;

	private UIFont defaultFont;

	public void Initialize() {
		defaultFont = new UIFont(DEFAULT_FONT);
	}

	public UIFont getFont(String name) {
		return getDefaultFont();
	}

	public UIFont getDefaultFont() {
		return defaultFont;
	}
}
