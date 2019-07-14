/** 
 * ??????
 * @param input
 * @return int CharacterUtil?????????
 */
static int identifyCharType(char input){
  if (input >= '0' && input <= '9') {
    return CHAR_ARABIC;
  }
 else   if ((input >= 'a' && input <= 'z') || (input >= 'A' && input <= 'Z')) {
    return CHAR_ENGLISH;
  }
 else {
    Character.UnicodeBlock ub=Character.UnicodeBlock.of(input);
    if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) {
      return CHAR_CHINESE;
    }
 else     if (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || ub == Character.UnicodeBlock.HANGUL_SYLLABLES || ub == Character.UnicodeBlock.HANGUL_JAMO || ub == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO || ub == Character.UnicodeBlock.HIRAGANA || ub == Character.UnicodeBlock.KATAKANA || ub == Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS) {
      return CHAR_OTHER_CJK;
    }
  }
  return CHAR_USELESS;
}
