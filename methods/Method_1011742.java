private Tuples._2<Integer,byte[]> backup(FileContent baseContent,FileContent localContent,FileContent latestContent){
  try {
    File zipModel=MergeDriverBackupUtil.zipModel(new byte[][]{baseContent.getData(),localContent.getData(),latestContent.getData()},myModelName);
    if (zipModel != null) {
      if (LOG.isInfoEnabled()) {
        LOG.info("Saved merge backup to " + zipModel);
      }
    }
  }
 catch (  IOException e) {
    if (LOG.isEnabledFor(Level.ERROR)) {
      LOG.error(String.format("%s: exception while backuping",myModelName),e);
    }
  }
  return null;
}
