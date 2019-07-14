@Override public List<TextFile> listFilesRecursively(){
  if (isFileSystem()) {
    assertExistsAndIsDirectory();
    List<File> fileList=newArrayList();
    recursivelyAddFilesToList(rootDirectory,fileList);
    return toTextFileList(fileList);
  }
  return FluentIterable.from(toIterable(zipFile.entries())).filter(new Predicate<ZipEntry>(){
    public boolean apply(    ZipEntry jarEntry){
      return !jarEntry.isDirectory() && jarEntry.getName().startsWith(path);
    }
  }
).transform(new Function<ZipEntry,TextFile>(){
    public TextFile apply(    ZipEntry jarEntry){
      return new TextFile(getUriFor(jarEntry));
    }
  }
).toList();
}
