protected String[] parseAliasAndClassName(String line){
  if (StringUtils.isBlank(line)) {
    return null;
  }
  line=line.trim();
  int i0=line.indexOf('#');
  if (i0 == 0 || line.length() == 0) {
    return null;
  }
  if (i0 > 0) {
    line=line.substring(0,i0).trim();
  }
  String alias=null;
  String className;
  int i=line.indexOf('=');
  if (i > 0) {
    alias=line.substring(0,i).trim();
    className=line.substring(i + 1).trim();
  }
 else {
    className=line;
  }
  if (className.length() == 0) {
    return null;
  }
  return new String[]{alias,className};
}
