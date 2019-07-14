@Override public void process(JanusGraphVertex vertex,ScanMetrics metrics){
  final PreloadedVertex v=(PreloadedVertex)vertex;
  if (vertexMemory != null) {
    VertexMemoryHandler vh=new VertexMemoryHandler(vertexMemory,v);
    v.setPropertyMixing(vh);
  }
  v.setAccessCheck(MAPREDUCE_CHECK);
  if (idManager.isPartitionedVertex(v.longId()) && !idManager.isCanonicalVertexId(v.longId())) {
    return;
  }
  for (  Map.Entry<MapReduce,FulgoraMapEmitter> mapJob : mapJobs.entrySet()) {
    final MapReduce job=mapJob.getKey();
    try {
      job.map(v,mapJob.getValue());
      metrics.incrementCustom(MAP_JOB_SUCCESS);
    }
 catch (    Throwable ex) {
      log.error("Encountered exception executing map job [" + job + "] on vertex [" + vertex + "]:",ex);
      metrics.incrementCustom(MAP_JOB_FAILURE);
    }
  }
}
