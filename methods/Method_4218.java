private static int getFramesPerEncodedSample(@C.Encoding int encoding,ByteBuffer buffer){
  if (encoding == C.ENCODING_DTS || encoding == C.ENCODING_DTS_HD) {
    return DtsUtil.parseDtsAudioSampleCount(buffer);
  }
 else   if (encoding == C.ENCODING_AC3) {
    return Ac3Util.getAc3SyncframeAudioSampleCount();
  }
 else   if (encoding == C.ENCODING_E_AC3) {
    return Ac3Util.parseEAc3SyncframeAudioSampleCount(buffer);
  }
 else   if (encoding == C.ENCODING_DOLBY_TRUEHD) {
    int syncframeOffset=Ac3Util.findTrueHdSyncframeOffset(buffer);
    return syncframeOffset == C.INDEX_UNSET ? 0 : (Ac3Util.parseTrueHdSyncframeAudioSampleCount(buffer,syncframeOffset) * Ac3Util.TRUEHD_RECHUNK_SAMPLE_COUNT);
  }
 else {
    throw new IllegalStateException("Unexpected audio encoding: " + encoding);
  }
}
