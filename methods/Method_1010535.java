@NotNull public static RealClassPathItem create(@NotNull String path,@Nullable String caller){
  IFile file=FS.getFile(path);
  String fPath=file.getPath();
  if (!file.exists()) {
    notifyNonExistentCP(caller,file,fPath);
    return new NonExistingClassPathItem(fPath);
  }
 else   if (path.endsWith(".jar") || path.endsWith("!/")) {
    return new JarFileClassPathItem(FS,fPath);
  }
 else   if (file.isDirectory()) {
    return new FileClassPathItem(fPath);
  }
 else   if (file.isInArchive()) {
    throw new IllegalArgumentException("Path variable `" + fPath + "' points to the location inside the jar which is not supported");
  }
 else {
    throw new IllegalArgumentException("Path variable `" + fPath + "' does not point to a directory or to a jar/zip location");
  }
}
