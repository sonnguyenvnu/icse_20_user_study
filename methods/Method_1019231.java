@Override public boolean accept(Path path){
  if (LOG.isDebugEnabled()) {
    LOG.debug("Checking acceptance for path " + path);
  }
  Path folder=null;
  try {
    if (fs == null) {
      fs=path.getFileSystem(new Configuration());
    }
    folder=path.getParent();
    if (nonHoodiePathCache.contains(folder.toString())) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("Accepting non-hoodie path from cache: " + path);
      }
      return true;
    }
    if (hoodiePathCache.containsKey(folder.toString())) {
      if (LOG.isDebugEnabled()) {
        LOG.debug(String.format("%s Hoodie path checked against cache, accept => %s \n",path,hoodiePathCache.get(folder.toString()).contains(path)));
      }
      return hoodiePathCache.get(folder.toString()).contains(path);
    }
    String filePath=path.toString();
    if (filePath.contains("/" + HoodieTableMetaClient.METAFOLDER_NAME + "/") || filePath.endsWith("/" + HoodieTableMetaClient.METAFOLDER_NAME)) {
      if (LOG.isDebugEnabled()) {
        LOG.debug(String.format("Skipping Hoodie Metadata file  %s \n",filePath));
      }
      return false;
    }
    Path baseDir;
    if (HoodiePartitionMetadata.hasPartitionMetadata(fs,folder)) {
      HoodiePartitionMetadata metadata=new HoodiePartitionMetadata(fs,folder);
      metadata.readFromFS();
      baseDir=HoodieHiveUtil.getNthParent(folder,metadata.getPartitionDepth());
    }
 else {
      baseDir=safeGetParentsParent(folder);
    }
    if (baseDir != null) {
      try {
        HoodieTableMetaClient metaClient=new HoodieTableMetaClient(fs.getConf(),baseDir.toString());
        HoodieTableFileSystemView fsView=new HoodieTableFileSystemView(metaClient,metaClient.getActiveTimeline().getCommitsTimeline().filterCompletedInstants(),fs.listStatus(folder));
        List<HoodieDataFile> latestFiles=fsView.getLatestDataFiles().collect(Collectors.toList());
        if (!hoodiePathCache.containsKey(folder.toString())) {
          hoodiePathCache.put(folder.toString(),new HashSet<>());
        }
        LOG.info("Based on hoodie metadata from base path: " + baseDir.toString() + ", caching " + latestFiles.size() + " files under " + folder);
        for (        HoodieDataFile lfile : latestFiles) {
          hoodiePathCache.get(folder.toString()).add(new Path(lfile.getPath()));
        }
        if (LOG.isDebugEnabled()) {
          LOG.debug(String.format("%s checked after cache population, accept => %s \n",path,hoodiePathCache.get(folder.toString()).contains(path)));
        }
        return hoodiePathCache.get(folder.toString()).contains(path);
      }
 catch (      DatasetNotFoundException e) {
        if (LOG.isDebugEnabled()) {
          LOG.debug(String.format("(1) Caching non-hoodie path under %s \n",folder.toString()));
        }
        nonHoodiePathCache.add(folder.toString());
        return true;
      }
    }
 else {
      if (LOG.isDebugEnabled()) {
        LOG.debug(String.format("(2) Caching non-hoodie path under %s \n",folder.toString()));
      }
      nonHoodiePathCache.add(folder.toString());
      return true;
    }
  }
 catch (  Exception e) {
    String msg="Error checking path :" + path + ", under folder: " + folder;
    LOG.error(msg,e);
    throw new HoodieException(msg,e);
  }
}
