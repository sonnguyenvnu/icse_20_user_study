private static void checkAndAddFolder(File dir,ObservableEmitter<Album> emitter,boolean includeVideo){
  File[] files=dir.listFiles(new ImageFileFilter(includeVideo));
  if (files != null && files.length > 0) {
    long lastMod=Long.MIN_VALUE;
    File choice=null;
    for (    File file : files) {
      if (file.lastModified() > lastMod) {
        choice=file;
        lastMod=file.lastModified();
      }
    }
    if (choice != null) {
      Album asd=new Album(dir.getAbsolutePath(),dir.getName(),files.length,lastMod);
      asd.setLastMedia(new Media(choice.getAbsolutePath()));
      emitter.onNext(asd);
    }
  }
}
