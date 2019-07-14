/** 
 * Unescapes an XML entity encoding;
 * @param e entity (only the actual entity value, not the preceding & or ending ;
 * @return
 */
static String unescapeEntity(String e){
  if (e == null || e.isEmpty()) {
    return "";
  }
  if (e.charAt(0) == '#') {
    int cp;
    if (e.charAt(1) == 'x') {
      cp=Integer.parseInt(e.substring(2),16);
    }
 else {
      cp=Integer.parseInt(e.substring(1));
    }
    return new String(new int[]{cp},0,1);
  }
  Character knownEntity=entity.get(e);
  if (knownEntity == null) {
    return '&' + e + ';';
  }
  return knownEntity.toString();
}
