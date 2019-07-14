public String getClassPath(){
  StringBuilder cp=new StringBuilder();
  String[] jarHeads=libraryFolder.list(jarFilter);
  for (  String jar : jarHeads) {
    cp.append(File.pathSeparatorChar);
    cp.append(new File(libraryFolder,jar).getAbsolutePath());
  }
  File nativeLibraryFolder=new File(nativeLibraryPath);
  if (!libraryFolder.equals(nativeLibraryFolder)) {
    jarHeads=new File(nativeLibraryPath).list(jarFilter);
    for (    String jar : jarHeads) {
      cp.append(File.pathSeparatorChar);
      cp.append(new File(nativeLibraryPath,jar).getAbsolutePath());
    }
  }
  return cp.toString();
}
