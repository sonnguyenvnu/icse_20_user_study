public static String convertToHongKongTraditionalChinese(String traditionalChineseString){
  return segLongest(traditionalChineseString.toCharArray(),trie);
}
