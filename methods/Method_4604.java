private boolean synchronize(ExtractorInput input,boolean sniffing) throws IOException, InterruptedException {
  int validFrameCount=0;
  int candidateSynchronizedHeaderData=0;
  int peekedId3Bytes=0;
  int searchedBytes=0;
  int searchLimitBytes=sniffing ? MAX_SNIFF_BYTES : MAX_SYNC_BYTES;
  input.resetPeekPosition();
  if (input.getPosition() == 0) {
    boolean parseAllId3Frames=(flags & FLAG_DISABLE_ID3_METADATA) == 0;
    Id3Decoder.FramePredicate id3FramePredicate=parseAllId3Frames ? null : REQUIRED_ID3_FRAME_PREDICATE;
    metadata=id3Peeker.peekId3Data(input,id3FramePredicate);
    if (metadata != null) {
      gaplessInfoHolder.setFromMetadata(metadata);
    }
    peekedId3Bytes=(int)input.getPeekPosition();
    if (!sniffing) {
      input.skipFully(peekedId3Bytes);
    }
  }
  while (true) {
    if (peekEndOfStreamOrHeader(input)) {
      if (validFrameCount > 0) {
        break;
      }
      throw new EOFException();
    }
    scratch.setPosition(0);
    int headerData=scratch.readInt();
    int frameSize;
    if ((candidateSynchronizedHeaderData != 0 && !headersMatch(headerData,candidateSynchronizedHeaderData)) || (frameSize=MpegAudioHeader.getFrameSize(headerData)) == C.LENGTH_UNSET) {
      if (searchedBytes++ == searchLimitBytes) {
        if (!sniffing) {
          throw new ParserException("Searched too many bytes.");
        }
        return false;
      }
      validFrameCount=0;
      candidateSynchronizedHeaderData=0;
      if (sniffing) {
        input.resetPeekPosition();
        input.advancePeekPosition(peekedId3Bytes + searchedBytes);
      }
 else {
        input.skipFully(1);
      }
    }
 else {
      validFrameCount++;
      if (validFrameCount == 1) {
        MpegAudioHeader.populateHeader(headerData,synchronizedHeader);
        candidateSynchronizedHeaderData=headerData;
      }
 else       if (validFrameCount == 4) {
        break;
      }
      input.advancePeekPosition(frameSize - 4);
    }
  }
  if (sniffing) {
    input.skipFully(peekedId3Bytes + searchedBytes);
  }
 else {
    input.resetPeekPosition();
  }
  synchronizedHeaderData=candidateSynchronizedHeaderData;
  return true;
}
