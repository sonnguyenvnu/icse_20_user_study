/** 
 * Peeks ID3 data from the input and parses the first ID3 tag.
 * @param input The {@link ExtractorInput} from which data should be peeked.
 * @param id3FramePredicate Determines which ID3 frames are decoded. May be null to decode allframes.
 * @return The first ID3 tag decoded into a {@link Metadata} object. May be null if ID3 tag is notpresent in the input.
 * @throws IOException If an error occurred peeking from the input.
 * @throws InterruptedException If the thread was interrupted.
 */
@Nullable public Metadata peekId3Data(ExtractorInput input,@Nullable Id3Decoder.FramePredicate id3FramePredicate) throws IOException, InterruptedException {
  int peekedId3Bytes=0;
  Metadata metadata=null;
  while (true) {
    try {
      input.peekFully(scratch.data,0,Id3Decoder.ID3_HEADER_LENGTH);
    }
 catch (    EOFException e) {
      break;
    }
    scratch.setPosition(0);
    if (scratch.readUnsignedInt24() != Id3Decoder.ID3_TAG) {
      break;
    }
    scratch.skipBytes(3);
    int framesLength=scratch.readSynchSafeInt();
    int tagLength=Id3Decoder.ID3_HEADER_LENGTH + framesLength;
    if (metadata == null) {
      byte[] id3Data=new byte[tagLength];
      System.arraycopy(scratch.data,0,id3Data,0,Id3Decoder.ID3_HEADER_LENGTH);
      input.peekFully(id3Data,Id3Decoder.ID3_HEADER_LENGTH,framesLength);
      metadata=new Id3Decoder(id3FramePredicate).decode(id3Data,tagLength);
    }
 else {
      input.advancePeekPosition(framesLength);
    }
    peekedId3Bytes+=tagLength;
  }
  input.resetPeekPosition();
  input.advancePeekPosition(peekedId3Bytes);
  return metadata;
}
