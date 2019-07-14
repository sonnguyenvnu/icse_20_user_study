public static String convertToTraditionalHongKongChinese(String simplifiedChineseString){
  return segLongest(simplifiedChineseString.toCharArray(),trie);
}
