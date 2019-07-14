@Nullable public static File getSDCardDir(@Nullable String relativePath){
  File parents[]=new File[]{Environment.getExternalStorageDirectory()};
  return getDir(parents,relativePath);
}
