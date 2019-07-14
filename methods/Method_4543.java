protected SeekOperationParams createSeekParamsForTargetTimeUs(long timeUs){
  return new SeekOperationParams(timeUs,seekMap.timeUsToTargetTime(timeUs),seekMap.floorTimePosition,seekMap.ceilingTimePosition,seekMap.floorBytePosition,seekMap.ceilingBytePosition,seekMap.approxBytesPerFrame);
}
