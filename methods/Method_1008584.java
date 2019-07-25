public FsInfo stats(FsInfo previous,@Nullable ClusterInfo clusterInfo) throws IOException {
  if (!nodeEnv.hasNodeFile()) {
    return new FsInfo(System.currentTimeMillis(),null,new FsInfo.Path[0]);
  }
  NodePath[] dataLocations=nodeEnv.nodePaths();
  FsInfo.Path[] paths=new FsInfo.Path[dataLocations.length];
  for (int i=0; i < dataLocations.length; i++) {
    paths[i]=getFSInfo(dataLocations[i]);
  }
  FsInfo.IoStats ioStats=null;
  if (Constants.LINUX) {
    Set<Tuple<Integer,Integer>> devicesNumbers=new HashSet<>();
    for (int i=0; i < dataLocations.length; i++) {
      if (dataLocations[i].majorDeviceNumber != -1 && dataLocations[i].minorDeviceNumber != -1) {
        devicesNumbers.add(Tuple.tuple(dataLocations[i].majorDeviceNumber,dataLocations[i].minorDeviceNumber));
      }
    }
    ioStats=ioStats(devicesNumbers,previous);
  }
  DiskUsage leastDiskEstimate=null;
  DiskUsage mostDiskEstimate=null;
  if (clusterInfo != null) {
    leastDiskEstimate=clusterInfo.getNodeLeastAvailableDiskUsages().get(nodeEnv.nodeId());
    mostDiskEstimate=clusterInfo.getNodeMostAvailableDiskUsages().get(nodeEnv.nodeId());
  }
  return new FsInfo(System.currentTimeMillis(),ioStats,paths,leastDiskEstimate,mostDiskEstimate);
}
