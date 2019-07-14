public static String convertToTaiwanChinese(String traditionalTaiwanChineseString){
  return segLongest(traditionalTaiwanChineseString.toCharArray(),trie);
}
