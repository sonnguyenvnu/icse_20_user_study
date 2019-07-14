private void cleanupFolders(){
  final AlertDialog progressDialog=new AlertDialog(getParentActivity(),3);
  progressDialog.setCanCacnel(false);
  progressDialog.show();
  Utilities.globalQueue.postRunnable(() -> {
    boolean imagesCleared=false;
    for (int a=0; a < 6; a++) {
      if (!clear[a]) {
        continue;
      }
      int type=-1;
      int documentsMusicType=0;
      if (a == 0) {
        type=FileLoader.MEDIA_DIR_IMAGE;
      }
 else       if (a == 1) {
        type=FileLoader.MEDIA_DIR_VIDEO;
      }
 else       if (a == 2) {
        type=FileLoader.MEDIA_DIR_DOCUMENT;
        documentsMusicType=1;
      }
 else       if (a == 3) {
        type=FileLoader.MEDIA_DIR_DOCUMENT;
        documentsMusicType=2;
      }
 else       if (a == 4) {
        type=FileLoader.MEDIA_DIR_AUDIO;
      }
 else       if (a == 5) {
        type=FileLoader.MEDIA_DIR_CACHE;
      }
      if (type == -1) {
        continue;
      }
      File file=FileLoader.checkDirectory(type);
      if (file != null) {
        Utilities.clearDir(file.getAbsolutePath(),documentsMusicType,Long.MAX_VALUE);
      }
      if (type == FileLoader.MEDIA_DIR_CACHE) {
        cacheSize=getDirectorySize(FileLoader.checkDirectory(FileLoader.MEDIA_DIR_CACHE),documentsMusicType);
        imagesCleared=true;
      }
 else       if (type == FileLoader.MEDIA_DIR_AUDIO) {
        audioSize=getDirectorySize(FileLoader.checkDirectory(FileLoader.MEDIA_DIR_AUDIO),documentsMusicType);
      }
 else       if (type == FileLoader.MEDIA_DIR_DOCUMENT) {
        if (documentsMusicType == 1) {
          documentsSize=getDirectorySize(FileLoader.checkDirectory(FileLoader.MEDIA_DIR_DOCUMENT),documentsMusicType);
        }
 else {
          musicSize=getDirectorySize(FileLoader.checkDirectory(FileLoader.MEDIA_DIR_DOCUMENT),documentsMusicType);
        }
      }
 else       if (type == FileLoader.MEDIA_DIR_IMAGE) {
        imagesCleared=true;
        photoSize=getDirectorySize(FileLoader.checkDirectory(FileLoader.MEDIA_DIR_IMAGE),documentsMusicType);
      }
 else       if (type == FileLoader.MEDIA_DIR_VIDEO) {
        videoSize=getDirectorySize(FileLoader.checkDirectory(FileLoader.MEDIA_DIR_VIDEO),documentsMusicType);
      }
    }
    final boolean imagesClearedFinal=imagesCleared;
    totalSize=cacheSize + videoSize + audioSize + photoSize + documentsSize + musicSize;
    AndroidUtilities.runOnUIThread(() -> {
      if (imagesClearedFinal) {
        ImageLoader.getInstance().clearMemory();
      }
      if (listAdapter != null) {
        listAdapter.notifyDataSetChanged();
      }
      try {
        progressDialog.dismiss();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
);
  }
);
}
