private Stats compareModtimes(FileSystem localFS,Path localPath,FileSystem destFS,Path destPath) throws IOException {
  Stats s=new Stats();
  s.local=localFS.getFileStatus(localPath);
  if (destFS.exists(destPath)) {
    s.dest=destFS.getFileStatus(destPath);
    if (null != s.dest && null != s.local) {
      long l=s.local.getModificationTime();
      long d=s.dest.getModificationTime();
      if (l == d) {
        if (log.isDebugEnabled())         log.debug("File {} with modtime {} is up-to-date",destPath,d);
      }
 else       if (l < d) {
        log.warn("File {} has newer modtime ({}) than our local copy {} ({})",destPath,d,localPath,l);
      }
 else {
        log.debug("Remote file {} exists but is out-of-date: local={} dest={}",destPath,l,d);
      }
    }
 else {
      log.debug("Unable to stat file(s): [LOCAL: path={} stat={}] [DEST: path={} stat={}]",localPath,s.local,destPath,s.dest);
    }
  }
 else {
    log.debug("File {} does not exist",destPath);
  }
  return s;
}
