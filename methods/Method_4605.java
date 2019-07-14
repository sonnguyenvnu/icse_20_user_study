/** 
 * Consumes the next frame from the  {@code input} if it contains VBRI or Xing seeking metadata,returning a  {@link Seeker} if the metadata was present and valid, or {@code null} otherwise.After this method returns, the input position is the start of the first frame of audio.
 * @param input The {@link ExtractorInput} from which to read.
 * @return A {@link Seeker} if seeking metadata was present and valid, or {@code null} otherwise.
 * @throws IOException Thrown if there was an error reading from the stream. Not expected if thenext two frames were already peeked during synchronization.
 * @throws InterruptedException Thrown if reading from the stream was interrupted. Not expected ifthe next two frames were already peeked during synchronization.
 */
private Seeker maybeReadSeekFrame(ExtractorInput input) throws IOException, InterruptedException {
  ParsableByteArray frame=new ParsableByteArray(synchronizedHeader.frameSize);
  input.peekFully(frame.data,0,synchronizedHeader.frameSize);
  int xingBase=(synchronizedHeader.version & 1) != 0 ? (synchronizedHeader.channels != 1 ? 36 : 21) : (synchronizedHeader.channels != 1 ? 21 : 13);
  int seekHeader=getSeekFrameHeader(frame,xingBase);
  Seeker seeker;
  if (seekHeader == SEEK_HEADER_XING || seekHeader == SEEK_HEADER_INFO) {
    seeker=XingSeeker.create(input.getLength(),input.getPosition(),synchronizedHeader,frame);
    if (seeker != null && !gaplessInfoHolder.hasGaplessInfo()) {
      input.resetPeekPosition();
      input.advancePeekPosition(xingBase + 141);
      input.peekFully(scratch.data,0,3);
      scratch.setPosition(0);
      gaplessInfoHolder.setFromXingHeaderValue(scratch.readUnsignedInt24());
    }
    input.skipFully(synchronizedHeader.frameSize);
    if (seeker != null && !seeker.isSeekable() && seekHeader == SEEK_HEADER_INFO) {
      return getConstantBitrateSeeker(input);
    }
  }
 else   if (seekHeader == SEEK_HEADER_VBRI) {
    seeker=VbriSeeker.create(input.getLength(),input.getPosition(),synchronizedHeader,frame);
    input.skipFully(synchronizedHeader.frameSize);
  }
 else {
    seeker=null;
    input.resetPeekPosition();
  }
  return seeker;
}
