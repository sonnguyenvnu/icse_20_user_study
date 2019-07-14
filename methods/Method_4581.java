private void commitSampleToOutput(Track track,long timeUs){
  if (track.trueHdSampleRechunker != null) {
    track.trueHdSampleRechunker.sampleMetadata(track,timeUs);
  }
 else {
    if (CODEC_ID_SUBRIP.equals(track.codecId)) {
      commitSubtitleSample(track,SUBRIP_TIMECODE_FORMAT,SUBRIP_PREFIX_END_TIMECODE_OFFSET,SUBRIP_TIMECODE_LAST_VALUE_SCALING_FACTOR,SUBRIP_TIMECODE_EMPTY);
    }
 else     if (CODEC_ID_ASS.equals(track.codecId)) {
      commitSubtitleSample(track,SSA_TIMECODE_FORMAT,SSA_PREFIX_END_TIMECODE_OFFSET,SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR,SSA_TIMECODE_EMPTY);
    }
    track.output.sampleMetadata(timeUs,blockFlags,sampleBytesWritten,0,track.cryptoData);
  }
  sampleRead=true;
  resetSample();
}
