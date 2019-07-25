private static String map(final String type,final boolean defaultPackage){
  if ("".equals(type)) {
    return type;
  }
  StringBuilder sb=new StringBuilder();
  int index=0;
  while ((index=type.indexOf("[]",index) + 1) > 0) {
    sb.append('[');
  }
  String t=type.substring(0,type.length() - sb.length() * 2);
  String desc=DESCRIPTORS.get(t);
  if (desc != null) {
    sb.append(desc);
  }
 else {
    sb.append('L');
    if (t.indexOf('.') < 0) {
      if (!defaultPackage) {
        sb.append("java/lang/");
      }
      sb.append(t);
    }
 else {
      sb.append(t.replace('.','/'));
    }
    sb.append(';');
  }
  return sb.toString();
}
