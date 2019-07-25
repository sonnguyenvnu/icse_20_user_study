@Override public Object clone(){
  return new LynxConfig().setMaxNumberOfTracesToShow(getMaxNumberOfTracesToShow()).setFilter(filter).setFilterTraceLevel(filterTraceLevel).setSamplingRate(getSamplingRate());
}
