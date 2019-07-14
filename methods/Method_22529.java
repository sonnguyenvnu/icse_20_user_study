static private boolean isFlagged(File folder,String flagFilename){
  return new File(folder,flagFilename).exists();
}
