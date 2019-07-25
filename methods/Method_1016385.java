private void mget(final String pattern,final boolean remove) throws IOException {
  final List<String> l=list(".",false);
  File local;
  for (  final String remote : l) {
    if (matches(remote,pattern)) {
      local=new File(this.currentLocalPath,remote);
      if (local.exists()) {
        Data.logger.warn("Warning: local file " + local.toString() + " overwritten.");
        if (!local.delete())         Data.logger.warn("Warning: local file " + local.toString() + " could not be deleted.");
      }
      retrieveFilesRecursively(remote,remove);
    }
  }
}
