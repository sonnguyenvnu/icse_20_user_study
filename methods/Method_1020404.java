private static MeasureMap record(Data data,Measure.MeasureLong measure,int value){
  MeasureMap map=data.recorder.newMeasureMap();
  map.put(measure,value);
  for (  TagContext tags : data.contexts) {
    map.record(tags);
  }
  return map;
}
