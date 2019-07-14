private File uriToFile(@NonNull Uri uri){
  if (mRootDir == null) {
    throw new IllegalStateException("Root directory is null");
  }
  File file=new File(mRootDir,uri.getEncodedPath());
  try {
    file=file.getCanonicalFile();
  }
 catch (  IOException e) {
    throw new IllegalArgumentException("Failed to get canonical file: " + file);
  }
  return file;
}
