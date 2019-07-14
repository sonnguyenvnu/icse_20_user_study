String matchingTrunkCode(String str){
  for (  String code : trunkPrefixes) {
    if (str.startsWith(code)) {
      return code;
    }
  }
  return null;
}
