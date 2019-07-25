private void paste(@NotNull CopyPasteFilesData data,@NotNull VirtualFile basedir){
  for (  VirtualFile f : data.getFiles()) {
    if (!FileTypeManager.getInstance().isFileIgnored(f.getName())) {
      ApplicationManager.getApplication().runWriteAction(() -> {
        try {
          if (!data.isCut()) {
            f.copy(this,basedir,f.getName());
          }
 else {
            f.move(this,basedir);
          }
        }
 catch (        IOException e) {
          LOG.error(String.format("Error while pasting %s %n",f),e);
        }
      }
);
    }
  }
}
