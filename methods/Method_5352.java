/** 
 * Peek the presentation timestamp of the first sample in the chunk from an ID3 PRIV as defined in the HLS spec, version 20, Section 3.4. Returns  {@link C#TIME_UNSET} if the frame is notfound. This method only modifies the peek position.
 * @param input The {@link ExtractorInput} to obtain the PRIV frame from.
 * @return The parsed, adjusted timestamp in microseconds
 * @throws IOException If an error occurred peeking from the input.
 * @throws InterruptedException If the thread was interrupted.
 */
private long peekId3PrivTimestamp(ExtractorInput input) throws IOException, InterruptedException {
  input.resetPeekPosition();
  try {
    input.peekFully(id3Data.data,0,Id3Decoder.ID3_HEADER_LENGTH);
  }
 catch (  EOFException e) {
    return C.TIME_UNSET;
  }
  id3Data.reset(Id3Decoder.ID3_HEADER_LENGTH);
  int id=id3Data.readUnsignedInt24();
  if (id != Id3Decoder.ID3_TAG) {
    return C.TIME_UNSET;
  }
  id3Data.skipBytes(3);
  int id3Size=id3Data.readSynchSafeInt();
  int requiredCapacity=id3Size + Id3Decoder.ID3_HEADER_LENGTH;
  if (requiredCapacity > id3Data.capacity()) {
    byte[] data=id3Data.data;
    id3Data.reset(requiredCapacity);
    System.arraycopy(data,0,id3Data.data,0,Id3Decoder.ID3_HEADER_LENGTH);
  }
  input.peekFully(id3Data.data,Id3Decoder.ID3_HEADER_LENGTH,id3Size);
  Metadata metadata=id3Decoder.decode(id3Data.data,id3Size);
  if (metadata == null) {
    return C.TIME_UNSET;
  }
  int metadataLength=metadata.length();
  for (int i=0; i < metadataLength; i++) {
    Metadata.Entry frame=metadata.get(i);
    if (frame instanceof PrivFrame) {
      PrivFrame privFrame=(PrivFrame)frame;
      if (PRIV_TIMESTAMP_FRAME_OWNER.equals(privFrame.owner)) {
        System.arraycopy(privFrame.privateData,0,id3Data.data,0,8);
        id3Data.reset(8);
        return id3Data.readLong() & 0x1FFFFFFFFL;
      }
    }
  }
  return C.TIME_UNSET;
}
