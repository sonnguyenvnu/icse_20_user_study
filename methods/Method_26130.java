private static String stripPrefixIfPresent(String originalText,String prefix){
  return originalText.startsWith(prefix) ? originalText.substring(prefix.length()) : originalText;
}
