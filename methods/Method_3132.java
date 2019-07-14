public static String convertToTraditionalTaiwanChinese(String simplifiedChineseString){
  return segLongest(simplifiedChineseString.toCharArray(),trie);
}
