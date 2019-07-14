private void nalUnitData(byte[] dataArray,int offset,int limit){
  if (!hasOutputFormat || sampleReader.needsSpsPps()) {
    sps.appendToNalUnit(dataArray,offset,limit);
    pps.appendToNalUnit(dataArray,offset,limit);
  }
  sei.appendToNalUnit(dataArray,offset,limit);
  sampleReader.appendToNalUnit(dataArray,offset,limit);
}
