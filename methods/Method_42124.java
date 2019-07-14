private static void fetchRecursivelyHiddenFolder(File dir,ObservableEmitter<Album> emitter,ArrayList<String> excludedAlbums,boolean includeVideo){
  if (!isExcluded(dir.getPath(),excludedAlbums)) {
    File[] folders=dir.listFiles(new FoldersFileFilter());
    if (folders != null) {
      for (      File temp : folders) {
        File nomedia=new File(temp,".nomedia");
        if (!isExcluded(temp.getPath(),excludedAlbums) && (nomedia.exists() || temp.isHidden()))         checkAndAddFolder(temp,emitter,includeVideo);
        fetchRecursivelyHiddenFolder(temp,emitter,excludedAlbums,includeVideo);
      }
    }
  }
}
