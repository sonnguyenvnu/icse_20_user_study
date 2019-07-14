static void unescape(StringBuilder original){
  int index=0;
  int semicolonIndex;
  String escaped;
  String entity;
  while (index < original.length()) {
    index=original.indexOf("&",index);
    if (-1 == index) {
      break;
    }
    semicolonIndex=original.indexOf(";",index);
    if (-1 != semicolonIndex) {
      escaped=original.substring(index,semicolonIndex + 1);
      entity=escapeEntityMap.get(escaped);
      if (entity != null) {
        original.replace(index,semicolonIndex + 1,entity);
      }
      index++;
    }
 else {
      break;
    }
  }
}
