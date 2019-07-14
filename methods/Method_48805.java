private void executeReducePhase(Map<MapReduce,FulgoraMapEmitter> mapJobs){
  for (  Map.Entry<MapReduce,FulgoraMapEmitter> mapJob : mapJobs.entrySet()) {
    FulgoraMapEmitter<?,?> mapEmitter=mapJob.getValue();
    MapReduce mapReduce=mapJob.getKey();
    mapEmitter.complete(mapReduce);
    if (mapReduce.doStage(MapReduce.Stage.REDUCE)) {
      final FulgoraReduceEmitter<?,?> reduceEmitter=new FulgoraReduceEmitter<>();
      try (WorkerPool workers=new WorkerPool(numThreads)){
        workers.submit(() -> mapReduce.workerStart(MapReduce.Stage.REDUCE));
        for (        final Map.Entry queueEntry : mapEmitter.reduceMap.entrySet()) {
          if (null == queueEntry)           break;
          workers.submit(() -> mapReduce.reduce(queueEntry.getKey(),((Iterable)queueEntry.getValue()).iterator(),reduceEmitter));
        }
        workers.submit(() -> mapReduce.workerEnd(MapReduce.Stage.REDUCE));
      }
 catch (      Exception e) {
        throw new JanusGraphException("Exception while executing reduce phase",e);
      }
      reduceEmitter.complete(mapReduce);
      mapReduce.addResultToMemory(this.memory,reduceEmitter.reduceQueue.iterator());
    }
 else {
      mapReduce.addResultToMemory(this.memory,mapEmitter.mapQueue.iterator());
    }
  }
}
