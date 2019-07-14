default JanusGraphComputer resultMode(ResultMode mode){
  result(mode.toResultGraph());
  persist(mode.toPersist());
  return this;
}
