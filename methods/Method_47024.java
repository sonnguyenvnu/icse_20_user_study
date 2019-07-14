@TargetApi(Build.VERSION_CODES.KITKAT) public static String[] getExtSdCardPathsForActivity(Context context){
  List<String> paths=new ArrayList<>();
  for (  File file : context.getExternalFilesDirs("external")) {
    if (file != null) {
      int index=file.getAbsolutePath().lastIndexOf("/Android/data");
      if (index < 0) {
        Log.w(LOG,"Unexpected external file dir: " + file.getAbsolutePath());
      }
 else {
        String path=file.getAbsolutePath().substring(0,index);
        try {
          path=new File(path).getCanonicalPath();
        }
 catch (        IOException e) {
        }
        paths.add(path);
      }
    }
  }
  if (paths.isEmpty())   paths.add("/storage/sdcard1");
  return paths.toArray(new String[0]);
}
