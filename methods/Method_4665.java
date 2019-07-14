public Track copyWithFormat(Format format){
  return new Track(id,type,timescale,movieTimescale,durationUs,format,sampleTransformation,sampleDescriptionEncryptionBoxes,nalUnitLengthFieldLength,editListDurations,editListMediaTimes);
}
