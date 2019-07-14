public static String convertToTraditionalTaiwanChinese(String traditionalHongKongChinese){
  return segLongest(traditionalHongKongChinese.toCharArray(),trie);
}
