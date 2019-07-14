public static String getPhotoPathRenamed(String olderPath,String newName){
  StringBuilder c=new StringBuilder();
  String b[]=olderPath.split("/");
  for (int x=0; x < b.length - 1; x++)   c.append(b[x]).append("/");
  c.append(newName);
  String name=b[b.length - 1];
  c.append(name.substring(name.lastIndexOf('.')));
  return c.toString();
}
