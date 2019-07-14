public static Executor getVertexMapJob(StandardJanusGraph graph,FulgoraVertexMemory vertexMemory,Map<MapReduce,FulgoraMapEmitter> mapJobs){
  VertexMapJob job=new VertexMapJob(graph.getIDManager(),vertexMemory,mapJobs);
  for (  Map.Entry<MapReduce,FulgoraMapEmitter> mapJob : mapJobs.entrySet()) {
    mapJob.getKey().workerStart(MapReduce.Stage.MAP);
  }
  return new Executor(graph,job);
}
