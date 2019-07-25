@Override public void build(@NotNull Project project,@NotNull Collection<FileObject> fileObjects){
  Collection<InputStream> memoryCache=new ArrayList<>();
  for (  FileObject fileObject : fileObjects) {
    InputStream inputStream;
    try {
      inputStream=new ResetOnCloseInputStream(new ByteArrayInputStream(IOUtils.getInputStreamBytes(fileObject.getContent().getInputStream())));
    }
 catch (    FileSystemException ignored) {
      continue;
    }
catch (    IOException ignored) {
      continue;
    }
    memoryCache.add(inputStream);
  }
  storage=new ServiceParameterStorage(memoryCache);
}
