private String extractContentTypeValue(final String type,int startIndex){
  while (startIndex < type.length() && type.charAt(startIndex) == ' ') {
    startIndex++;
  }
  if (startIndex >= type.length()) {
    return null;
  }
  int endIndex=startIndex;
  if (type.charAt(startIndex) == '"') {
    startIndex++;
    endIndex=type.indexOf('"',startIndex);
    if (endIndex == -1) {
      endIndex=type.length();
    }
  }
 else {
    while (endIndex < type.length() && (!ArraysUtil.contains(TSPECIALS,type.charAt(endIndex)))) {
      endIndex++;
    }
  }
  return type.substring(startIndex,endIndex);
}
