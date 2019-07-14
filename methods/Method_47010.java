public static String convertName(FileHeader file){
  String name=file.getFileNameString().replace('\\','/');
  if (file.isDirectory())   return name + SEPARATOR;
 else   return name;
}
