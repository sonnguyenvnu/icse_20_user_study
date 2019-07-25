public void refresh(){
  for (  String path : paths) {
    List<NutResource> list=Scans.me().scan(path,regex);
    for (    NutResource res : list) {
      int c=sqls.size();
      log.debugf("load >> %s from root=%s",res.getName(),path);
      try {
        add(res.getReader());
      }
 catch (      IOException e) {
        log.warnf("fail to load %s from root=%s",res.getName(),path,e);
      }
      log.debugf("load %d sql >> %s from root=%s",(sqls.size() - c),res.getName(),path);
    }
  }
}
