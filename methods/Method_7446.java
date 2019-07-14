String matchingAccessCode(String str){
  for (  String code : intlPrefixes) {
    if (str.startsWith(code)) {
      return code;
    }
  }
  return null;
}
