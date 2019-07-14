public static String getSdcardPath(Context context){
  for (  File file : context.getExternalFilesDirs("external")) {
    if (file != null && !file.equals(context.getExternalFilesDir("external"))) {
      int index=file.getAbsolutePath().lastIndexOf("/Android/data");
      if (index < 0)       Log.w("asd","Unexpected external file dir: " + file.getAbsolutePath());
 else       return new File(file.getAbsolutePath().substring(0,index)).getPath();
    }
  }
  return null;
}
