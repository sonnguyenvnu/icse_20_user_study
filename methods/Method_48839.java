public static <M>Executor getVertexProgramScanJob(StandardJanusGraph graph,FulgoraMemory memory,FulgoraVertexMemory vertexMemory,VertexProgram<M> vertexProgram){
  final VertexProgramScanJob<M> job=new VertexProgramScanJob<>(graph.getIDManager(),memory,vertexMemory,vertexProgram);
  return new Executor(graph,job);
}
