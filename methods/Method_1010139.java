public void update(@Nullable String path,@NotNull String fileName){
  myFiles.getOrAdd(new FilesEntry(path)).addFile(fileName);
}
