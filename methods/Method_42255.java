public static String getPhotoPathRenamedAlbumChange(String olderPath,String albumNewName){
  String c="", b[]=olderPath.split("/");
  for (int x=0; x < b.length - 2; x++)   c+=b[x] + "/";
  c+=albumNewName + "/" + b[b.length - 1];
  return c;
}
