static void clearRestartFlags(File folder){
  File restartFlag=new File(folder,RESTART_FLAG);
  if (restartFlag.exists()) {
    restartFlag.delete();
  }
}
