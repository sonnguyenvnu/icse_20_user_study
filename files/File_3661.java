package com.hankcs.hanlp.utility;

/**
 * 字符集识别辅助工具类
 */
public class CharacterHelper
{

    public static boolean isSpaceLetter(char input)
    {
        return input == 8 || input == 9
                || input == 10 || input == 13
                || input == 32 || input == 160;
    }

    public static boolean isEnglishLetter(char input)
    {
        return (input >= 'a' && input <= 'z')
                || (input >= 'A' && input <= 'Z');
    }

    public static boolean isArabicNumber(char input)
    {
        return input >= '0' && input <= '9';
    }

    public static boolean isCJKCharacter(char input)
    {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(input);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                //全角数字字符和日韩字符
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                //韩文字符集
                || ub == Character.UnicodeBlock.HANGUL_SYLLABLES
                || ub == Character.UnicodeBlock.HANGUL_JAMO
                || ub == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO
                //日文字符集
                || ub == Character.UnicodeBlock.HIRAGANA //平�?��??
                || ub == Character.UnicodeBlock.KATAKANA //片�?��??
                || ub == Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS
                )
        {
            return true;
        }
        else
        {
            return false;
        }
        //其他的CJK标点符�?�，�?�以�?�?�处�?�
        //|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
        //|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
    }


    /**
     * 进行字符规格化（全角转�?�角，大写转�?写处�?�）
     *
     * @param input
     * @return char
     */
    public static char regularize(char input)
    {
        if (input == 12288)
        {
            input = (char) 32;

        }
        else if (input > 65280 && input < 65375)
        {
            input = (char) (input - 65248);

        }
        else if (input >= 'A' && input <= 'Z')
        {
            input += 32;
        }

        return input;
    }

}
