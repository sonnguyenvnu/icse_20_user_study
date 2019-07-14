public static String convertToSimplifiedChinese(String traditionalChineseString){
  return segLongest(traditionalChineseString.toCharArray(),trie);
}
