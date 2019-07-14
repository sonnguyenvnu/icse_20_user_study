public static String incrementFileNameSuffix(String name){
  StringBuilder builder=new StringBuilder();
  int dot=name.lastIndexOf('.');
  String baseName=dot != -1 ? name.subSequence(0,dot).toString() : name;
  String nameWoSuffix=baseName;
  Matcher matcher=Pattern.compile("_\\d").matcher(baseName);
  if (matcher.find()) {
    int i=baseName.lastIndexOf("_");
    if (i != -1)     nameWoSuffix=baseName.subSequence(0,i).toString();
  }
  builder.append(nameWoSuffix).append("_").append(new Date().getTime());
  builder.append(name.substring(dot));
  return builder.toString();
}
