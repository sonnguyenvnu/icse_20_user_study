private static MeasureMap record(Data data,Measure.MeasureDouble measure,double value){
  MeasureMap map=data.recorder.newMeasureMap();
  map.put(measure,value).record(data.tagContext);
  return map;
}
