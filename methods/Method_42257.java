public static String getBucketPathByImagePath(String path){
  String b[]=path.split("/");
  String c="";
  for (int x=0; x < b.length - 1; x++)   c+=b[x] + "/";
  c=c.substring(0,c.length() - 1);
  return c;
}
