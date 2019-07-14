@VisibleForTesting VorbisSetup readSetupHeaders(ParsableByteArray scratch) throws IOException {
  if (vorbisIdHeader == null) {
    vorbisIdHeader=VorbisUtil.readVorbisIdentificationHeader(scratch);
    return null;
  }
  if (commentHeader == null) {
    commentHeader=VorbisUtil.readVorbisCommentHeader(scratch);
    return null;
  }
  byte[] setupHeaderData=new byte[scratch.limit()];
  System.arraycopy(scratch.data,0,setupHeaderData,0,scratch.limit());
  Mode[] modes=VorbisUtil.readVorbisModes(scratch,vorbisIdHeader.channels);
  int iLogModes=VorbisUtil.iLog(modes.length - 1);
  return new VorbisSetup(vorbisIdHeader,commentHeader,setupHeaderData,modes,iLogModes);
}
