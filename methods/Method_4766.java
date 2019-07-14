private void endNalUnit(long position,int offset,int discardPadding,long pesTimeUs){
  if (!hasOutputFormat || sampleReader.needsSpsPps()) {
    sps.endNalUnit(discardPadding);
    pps.endNalUnit(discardPadding);
    if (!hasOutputFormat) {
      if (sps.isCompleted() && pps.isCompleted()) {
        List<byte[]> initializationData=new ArrayList<>();
        initializationData.add(Arrays.copyOf(sps.nalData,sps.nalLength));
        initializationData.add(Arrays.copyOf(pps.nalData,pps.nalLength));
        NalUnitUtil.SpsData spsData=NalUnitUtil.parseSpsNalUnit(sps.nalData,3,sps.nalLength);
        NalUnitUtil.PpsData ppsData=NalUnitUtil.parsePpsNalUnit(pps.nalData,3,pps.nalLength);
        output.format(Format.createVideoSampleFormat(formatId,MimeTypes.VIDEO_H264,CodecSpecificDataUtil.buildAvcCodecString(spsData.profileIdc,spsData.constraintsFlagsAndReservedZero2Bits,spsData.levelIdc),Format.NO_VALUE,Format.NO_VALUE,spsData.width,spsData.height,Format.NO_VALUE,initializationData,Format.NO_VALUE,spsData.pixelWidthAspectRatio,null));
        hasOutputFormat=true;
        sampleReader.putSps(spsData);
        sampleReader.putPps(ppsData);
        sps.reset();
        pps.reset();
      }
    }
 else     if (sps.isCompleted()) {
      NalUnitUtil.SpsData spsData=NalUnitUtil.parseSpsNalUnit(sps.nalData,3,sps.nalLength);
      sampleReader.putSps(spsData);
      sps.reset();
    }
 else     if (pps.isCompleted()) {
      NalUnitUtil.PpsData ppsData=NalUnitUtil.parsePpsNalUnit(pps.nalData,3,pps.nalLength);
      sampleReader.putPps(ppsData);
      pps.reset();
    }
  }
  if (sei.endNalUnit(discardPadding)) {
    int unescapedLength=NalUnitUtil.unescapeStream(sei.nalData,sei.nalLength);
    seiWrapper.reset(sei.nalData,unescapedLength);
    seiWrapper.setPosition(4);
    seiReader.consume(pesTimeUs,seiWrapper);
  }
  boolean sampleIsKeyFrame=sampleReader.endNalUnit(position,offset,hasOutputFormat,randomAccessIndicator);
  if (sampleIsKeyFrame) {
    randomAccessIndicator=false;
  }
}
