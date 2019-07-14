@Override protected SsaSubtitle decode(byte[] bytes,int length,boolean reset){
  ArrayList<Cue> cues=new ArrayList<>();
  LongArray cueTimesUs=new LongArray();
  ParsableByteArray data=new ParsableByteArray(bytes,length);
  if (!haveInitializationData) {
    parseHeader(data);
  }
  parseEventBody(data,cues,cueTimesUs);
  Cue[] cuesArray=new Cue[cues.size()];
  cues.toArray(cuesArray);
  long[] cueTimesUsArray=cueTimesUs.toArray();
  return new SsaSubtitle(cuesArray,cueTimesUsArray);
}
