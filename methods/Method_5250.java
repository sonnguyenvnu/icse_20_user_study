protected Period buildPeriod(String id,long startMs,List<AdaptationSet> adaptationSets,List<EventStream> eventStreams){
  return new Period(id,startMs,adaptationSets,eventStreams);
}
