private String resolveBytecodeName(String typeName){
  int ndx=0;
  int genericsStartNdx=-1;
  int bracketCount=0;
  while (ndx < typeName.length()) {
    final char c=typeName.charAt(ndx);
    if (c == '<') {
      if (bracketCount == 0) {
        genericsStartNdx=ndx;
      }
      bracketCount++;
      ndx++;
      continue;
    }
    if (c == '>') {
      bracketCount--;
      if (bracketCount == 0) {
        break;
      }
    }
    ndx++;
  }
  if (genericsStartNdx != -1) {
    typeName=typeName.substring(0,genericsStartNdx) + typeName.substring(ndx + 1);
  }
  if (isGenericType(typeName)) {
    return typeName;
  }
  return 'L' + typeName.replace('.','/') + ';';
}
