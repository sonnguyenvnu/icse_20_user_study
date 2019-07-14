public static String convertToTraditionalChinese(String simplifiedChineseString){
  return segLongest(simplifiedChineseString.toCharArray(),trie);
}
