public static String convertToTraditionalChinese(String traditionalHongKongChineseString){
  return segLongest(traditionalHongKongChineseString.toCharArray(),trie);
}
