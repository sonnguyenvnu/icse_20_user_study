@Override public void onPostExecute(Boolean movedCorrectly){
  if (movedCorrectly) {
    if (mainFrag != null && mainFrag.getCurrentPath().equals(paths.get(0))) {
      Intent intent=new Intent(MainActivity.KEY_INTENT_LOAD_LIST);
      intent.putExtra(MainActivity.KEY_INTENT_LOAD_LIST_FILE,paths.get(0));
      context.sendBroadcast(intent);
    }
    for (int i=0; i < paths.size(); i++) {
      for (      HybridFileParcelable f : files.get(i)) {
        FileUtils.scanFile(f.getFile(),context);
        FileUtils.scanFile(new File(paths.get(i) + "/" + f.getName()),context);
      }
    }
    AppConfig.runInBackground(() -> {
      for (int i=0; i < paths.size(); i++) {
        for (        HybridFileParcelable file : files.get(i)) {
          if (file.getName().endsWith(CryptUtil.CRYPT_EXTENSION)) {
            try {
              CryptHandler cryptHandler=new CryptHandler(context);
              EncryptedEntry oldEntry=cryptHandler.findEntry(file.getPath());
              EncryptedEntry newEntry=new EncryptedEntry();
              newEntry.setId(oldEntry.getId());
              newEntry.setPassword(oldEntry.getPassword());
              newEntry.setPath(paths.get(i) + "/" + file.getName());
              cryptHandler.updateEntry(oldEntry,newEntry);
            }
 catch (            Exception e) {
              e.printStackTrace();
            }
          }
        }
      }
    }
);
  }
 else {
    if (destinationSize < totalBytes) {
      Toast.makeText(context,context.getResources().getString(R.string.in_safe),Toast.LENGTH_LONG).show();
      return;
    }
    for (int i=0; i < paths.size(); i++) {
      Intent intent=new Intent(context,CopyService.class);
      intent.putExtra(CopyService.TAG_COPY_SOURCES,files.get(i));
      intent.putExtra(CopyService.TAG_COPY_TARGET,paths.get(i));
      intent.putExtra(CopyService.TAG_COPY_MOVE,true);
      intent.putExtra(CopyService.TAG_COPY_OPEN_MODE,mode.ordinal());
      intent.putExtra(CopyService.TAG_IS_ROOT_EXPLORER,mainFrag.getMainActivity().isRootExplorer());
      ServiceWatcherUtil.runService(context,intent);
    }
  }
}
