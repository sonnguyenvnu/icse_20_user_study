@Nullable public File getFilesDir(@Nullable String relativePath){
  File externalFilesDirs[]=ContextCompat.getExternalFilesDirs(context,null);
  if (externalFilesDirs == null) {
    Log.e("BaseSystem","getFilesDir: getExternalFilesDirs returned null");
    return null;
  }
  return FileUtils.getDir(externalFilesDirs,relativePath);
}
