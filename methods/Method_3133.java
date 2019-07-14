public static String convertToTraditionalTaiwanChinese(char[] simplifiedChinese){
  return segLongest(simplifiedChinese,trie);
}
