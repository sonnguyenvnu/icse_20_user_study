public static HashSet<File> getStorageRoots(Context context){
  HashSet<File> paths=new HashSet<File>();
  for (  File file : context.getExternalFilesDirs("external")) {
    if (file != null) {
      int index=file.getAbsolutePath().lastIndexOf("/Android/data");
      if (index < 0)       Log.w("asd","Unexpected external file dir: " + file.getAbsolutePath());
 else       paths.add(new File(file.getAbsolutePath().substring(0,index)));
    }
  }
  return paths;
}
