private void startNalUnit(long position,int offset,int nalUnitType,long pesTimeUs){
  if (hasOutputFormat) {
    sampleReader.startNalUnit(position,offset,nalUnitType,pesTimeUs);
  }
 else {
    vps.startNalUnit(nalUnitType);
    sps.startNalUnit(nalUnitType);
    pps.startNalUnit(nalUnitType);
  }
  prefixSei.startNalUnit(nalUnitType);
  suffixSei.startNalUnit(nalUnitType);
}
