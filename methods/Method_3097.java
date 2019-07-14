public static List<Pinyin> convertToPinyin(String text,boolean remainNone){
  return segLongest(text.toCharArray(),trie,remainNone);
}
