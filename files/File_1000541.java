package org.nutz.img;

import java.awt.Font;

import org.nutz.lang.OS;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;

/**
 * 字体相关
 * 
 * 
 * @author pw
 *
 */
public class Fonts {

    private static NutMap fontMap = NutMap.NEW();

    /**
     * 列出了常用�?作系统的自带的字体
     */
    private static final String[] commonFonts = new String[]{
                                                             // windows
                                                             "微软雅黑",
                                                             "微软正黑体",
                                                             "黑体",
                                                             "宋体",
                                                             "仿宋",
                                                             "新宋体",
                                                             "楷体",
                                                             "仿宋GB2312",
                                                             "楷体GB2312",
                                                             "Microsoft YaHei",
                                                             "Microsoft YaHei UI",
                                                             "Microsoft JhengHei",
                                                             "SimHei",
                                                             "SimSun",
                                                             "FangSong",
                                                             "NSimSun",
                                                             "FangSongGB2312",
                                                             "KaiTiGB2312",
                                                             // macOS
                                                             "冬�?�黑体",
                                                             "Hiragino Sans GB",
                                                             "STHeiti",
                                                             "STSong",
                                                             "STFangsong",
                                                             "STKait",
                                                             "Apple LiGothic Medium",
                                                             "Apple LiSung Light",
                                                             "LiHei Pro Medium",
                                                             "LiSong Pro Light",
                                                             // ubuntu
                                                             "Dialog",
                                                             "Serif",
                                                             "SansSerif",
                                                             "Monospaced",
                                                             "Lucida Sans Typewriter",
                                                             "DialogInput",
                                                             "Lucida Bright",
                                                             "Lucida Sans",
                                                             // centos
                                                             "Abyssinica SIL",
                                                             "AR PL UMing CN",
                                                             "AR PL UMing HK",
                                                             "AR PL UMing TW",
                                                             "AR PL UMing TW MBE",
                                                             "Bitstream Charter",
                                                             "Caladea",
                                                             "Cantarell",
                                                             "Carlito",
                                                             "Century Schoolbook L",
                                                             "Courier 10 Pitch",
                                                             "Cursor",
                                                             "DejaVu Sans",
                                                             "DejaVu Sans Condensed",
                                                             "DejaVu Sans Light",
                                                             "DejaVu Sans Mono",
                                                             "DejaVu Serif",
                                                             "DejaVu Serif Condensed",
                                                             "Dialog",
                                                             "DialogInput",
                                                             "Dingbats",
                                                             "FreeMono",
                                                             "FreeSans",
                                                             "FreeSerif",
                                                             "Jomolhari",
                                                             "Khmer OS",
                                                             "Khmer OS Content",
                                                             "Khmer OS System",
                                                             "Liberation Mono",
                                                             "Liberation Sans",
                                                             "Liberation Serif",
                                                             "LKLUG",
                                                             "Lohit Assamese",
                                                             "Lohit Bengali",
                                                             "Lohit Devanagari",
                                                             "Lohit Gujarati",
                                                             "Lohit Kannada",
                                                             "Lohit Malayalam",
                                                             "Lohit Marathi",
                                                             "Lohit Nepali",
                                                             "Lohit Oriya",
                                                             "Lohit Punjabi",
                                                             "Lohit Tamil",
                                                             "Lohit Telugu",
                                                             "Lucida Bright",
                                                             "Lucida Sans",
                                                             "Lucida Sans Typewriter",
                                                             "Madan2",
                                                             "Meera",
                                                             "Monospaced",
                                                             "NanumGothic",
                                                             "NanumGothicExtraBold",
                                                             "Nimbus Mono L",
                                                             "Nimbus Roman No9 L",
                                                             "Nimbus Sans L",
                                                             "Nuosu SIL",
                                                             "Open Sans",
                                                             "Open Sans Extrabold",
                                                             "Open Sans Light",
                                                             "Open Sans Semibold",
                                                             "OpenSymbol",
                                                             "Overpass",
                                                             "Padauk",
                                                             "PakType Naskh Basic",
                                                             "PT Sans",
                                                             "PT Sans Narrow",
                                                             "SansSerif",
                                                             "Serif",
                                                             "Standard Symbols L",
                                                             "STIX",
                                                             "URW Bookman L",
                                                             "URW Chancery L",
                                                             "URW Gothic L",
                                                             "URW Palladio L",
                                                             "Utopia",
                                                             "VL Gothic",
                                                             "Waree",
                                                             "WenQuanYi Micro Hei",
                                                             "WenQuanYi Micro Hei Mono",
                                                             "WenQuanYi Zen Hei",
                                                             "WenQuanYi Zen Hei Mono",
                                                             "WenQuanYi Zen Hei Sharp"};

    static {
        String[] fonts = OS.fontsRefresh(); // 获得系统字体
        for (String fnm : fonts) {
            fontMap.addv(fnm, true);
        }
    }

    /**
     * 获得�?机字体
     * 
     * @param style
     *            字体样�? 支�?：Font.PLAIN Font.BOLD Font.ITALIC
     * @param size
     *            字体大�?
     * @return 字体
     */
    public static Font random(int style, int size) {
        Font font = null;
        while (font == null) {
            try {
                int index = R.random(0, commonFonts.length - 1);
                font = get(commonFonts[index], style, size);
            }
            catch (Exception e) {}
        }
        return font;
    }

    /**
     * 获得指定字体
     * 
     * @param name
     *            字体�??称
     * @param style
     *            字体样�? 支�?：Font.PLAIN Font.BOLD Font.ITALIC
     * @param size
     *            字体大�?
     * @return 字体
     */
    public static Font get(String name, int style, int size) {
        if (Strings.isBlank(name)) {
            // �?试微软雅黑，黑体，宋体等常�?字体
            Font ff = find(commonFonts, style, size);
            if (ff == null) {
                throw new RuntimeException("Please manually set the font, or add some common fonts in the system");
            }
            return ff;
        }
        return new Font(name, style, size);
    }

    /**
     * 检查当�?系统中是�?�有该字体
     * 
     * @param name
     *            字体�??称
     * @return 判断结果
     */
    public static boolean exist(String name) {
        return fontMap.getBoolean(name, false);
    }

    private static Font find(String[] fnames, int style, int size) {
        for (String name : fnames) {
            if (exist(name)) {
                return new Font(name, style, size);
            }
        }
        return null;
    }
}
