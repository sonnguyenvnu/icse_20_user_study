protected EventStream buildEventStream(String schemeIdUri,String value,long timescale,long[] presentationTimesUs,EventMessage[] events){
  return new EventStream(schemeIdUri,value,timescale,presentationTimesUs,events);
}
