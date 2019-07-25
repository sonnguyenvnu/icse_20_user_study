public static String decipher(String path){
  path=path.replaceAll("(\\S)-","$1 -");
  path=path.replaceAll("([a-zA-Z])(\\S)","$1 $2");
  path=path.replaceAll("(\\S)([a-zA-Z])","$1 $2");
  path=path.replaceAll("([a-zA-Z])(\\S)","$1 $2");
  while (path.matches(".*\\.\\d+\\..*")) {
    path=path.replaceAll("(\\.\\d+)\\.","$1 .");
  }
  return path;
}
