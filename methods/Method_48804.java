private Map<MapReduce,FulgoraMapEmitter> collectMapJobs(){
  Map<MapReduce,FulgoraMapEmitter> mapJobs=new HashMap<>(mapReduces.size());
  for (  MapReduce mapReduce : mapReduces) {
    if (mapReduce.doStage(MapReduce.Stage.MAP)) {
      FulgoraMapEmitter mapEmitter=new FulgoraMapEmitter<>(mapReduce.doStage(MapReduce.Stage.REDUCE));
      mapJobs.put(mapReduce,mapEmitter);
    }
  }
  return mapJobs;
}
