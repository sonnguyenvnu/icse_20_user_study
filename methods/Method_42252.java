public static String getName(String path){
  String b[]=path.split("/");
  return b[b.length - 1];
}
