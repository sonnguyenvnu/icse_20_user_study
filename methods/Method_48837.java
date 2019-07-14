@Override public void workerIterationEnd(ScanMetrics metrics){
  vertexProgram.workerIterationEnd(memory.asImmutable());
}
