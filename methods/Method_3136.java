public static String convertToTraditionalChinese(String traditionalTaiwanChineseString){
  return segLongest(traditionalTaiwanChineseString.toCharArray(),trie);
}
