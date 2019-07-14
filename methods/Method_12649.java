public static boolean isCJKCharacter(char input){
  Character.UnicodeBlock ub=Character.UnicodeBlock.of(input);
  if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || ub == Character.UnicodeBlock.HANGUL_SYLLABLES || ub == Character.UnicodeBlock.HANGUL_JAMO || ub == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO || ub == Character.UnicodeBlock.HIRAGANA || ub == Character.UnicodeBlock.KATAKANA || ub == Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS) {
    return true;
  }
 else {
    return false;
  }
}
