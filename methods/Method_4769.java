private void nalUnitData(byte[] dataArray,int offset,int limit){
  if (hasOutputFormat) {
    sampleReader.readNalUnitData(dataArray,offset,limit);
  }
 else {
    vps.appendToNalUnit(dataArray,offset,limit);
    sps.appendToNalUnit(dataArray,offset,limit);
    pps.appendToNalUnit(dataArray,offset,limit);
  }
  prefixSei.appendToNalUnit(dataArray,offset,limit);
  suffixSei.appendToNalUnit(dataArray,offset,limit);
}
