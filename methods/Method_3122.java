public static String convertToSimplifiedChinese(String traditionalHongKongChineseString){
  return segLongest(traditionalHongKongChineseString.toCharArray(),trie);
}
