@Override public void run(){
  LOG.info("Refreshing " + myFiles.size() + " file(s)");
  refreshRecursivelyIntoJars(myFiles);
  LOG.info("Refreshing is done");
}
