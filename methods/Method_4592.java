/** 
 * @see com.google.android.exoplayer2.extractor.Extractor#sniff(ExtractorInput)
 */
public boolean sniff(ExtractorInput input) throws IOException, InterruptedException {
  long inputLength=input.getLength();
  int bytesToSearch=(int)(inputLength == C.LENGTH_UNSET || inputLength > SEARCH_LENGTH ? SEARCH_LENGTH : inputLength);
  input.peekFully(scratch.data,0,4);
  long tag=scratch.readUnsignedInt();
  peekLength=4;
  while (tag != ID_EBML) {
    if (++peekLength == bytesToSearch) {
      return false;
    }
    input.peekFully(scratch.data,0,1);
    tag=(tag << 8) & 0xFFFFFF00;
    tag|=scratch.data[0] & 0xFF;
  }
  long headerSize=readUint(input);
  long headerStart=peekLength;
  if (headerSize == Long.MIN_VALUE || (inputLength != C.LENGTH_UNSET && headerStart + headerSize >= inputLength)) {
    return false;
  }
  while (peekLength < headerStart + headerSize) {
    long id=readUint(input);
    if (id == Long.MIN_VALUE) {
      return false;
    }
    long size=readUint(input);
    if (size < 0 || size > Integer.MAX_VALUE) {
      return false;
    }
    if (size != 0) {
      int sizeInt=(int)size;
      input.advancePeekPosition(sizeInt);
      peekLength+=sizeInt;
    }
  }
  return peekLength == headerStart + headerSize;
}
