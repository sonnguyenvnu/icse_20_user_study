void record(TagContext tags,MeasureMapInternal measurementValues){
  if (state.getInternal() == State.ENABLED) {
    queue.enqueue(new StatsEvent(this,tags,measurementValues));
  }
}
