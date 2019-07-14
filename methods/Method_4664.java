private static boolean sniffInternal(ExtractorInput input,boolean fragmented) throws IOException, InterruptedException {
  long inputLength=input.getLength();
  int bytesToSearch=(int)(inputLength == C.LENGTH_UNSET || inputLength > SEARCH_LENGTH ? SEARCH_LENGTH : inputLength);
  ParsableByteArray buffer=new ParsableByteArray(64);
  int bytesSearched=0;
  boolean foundGoodFileType=false;
  boolean isFragmented=false;
  while (bytesSearched < bytesToSearch) {
    int headerSize=Atom.HEADER_SIZE;
    buffer.reset(headerSize);
    input.peekFully(buffer.data,0,headerSize);
    long atomSize=buffer.readUnsignedInt();
    int atomType=buffer.readInt();
    if (atomSize == Atom.DEFINES_LARGE_SIZE) {
      headerSize=Atom.LONG_HEADER_SIZE;
      input.peekFully(buffer.data,Atom.HEADER_SIZE,Atom.LONG_HEADER_SIZE - Atom.HEADER_SIZE);
      buffer.setLimit(Atom.LONG_HEADER_SIZE);
      atomSize=buffer.readLong();
    }
 else     if (atomSize == Atom.EXTENDS_TO_END_SIZE) {
      long fileEndPosition=input.getLength();
      if (fileEndPosition != C.LENGTH_UNSET) {
        atomSize=fileEndPosition - input.getPeekPosition() + headerSize;
      }
    }
    if (inputLength != C.LENGTH_UNSET && bytesSearched + atomSize > inputLength) {
      return false;
    }
    if (atomSize < headerSize) {
      return false;
    }
    bytesSearched+=headerSize;
    if (atomType == Atom.TYPE_moov) {
      bytesToSearch+=(int)atomSize;
      if (inputLength != C.LENGTH_UNSET && bytesToSearch > inputLength) {
        bytesToSearch=(int)inputLength;
      }
      continue;
    }
    if (atomType == Atom.TYPE_moof || atomType == Atom.TYPE_mvex) {
      isFragmented=true;
      break;
    }
    if (bytesSearched + atomSize - headerSize >= bytesToSearch) {
      break;
    }
    int atomDataSize=(int)(atomSize - headerSize);
    bytesSearched+=atomDataSize;
    if (atomType == Atom.TYPE_ftyp) {
      if (atomDataSize < 8) {
        return false;
      }
      buffer.reset(atomDataSize);
      input.peekFully(buffer.data,0,atomDataSize);
      int brandsCount=atomDataSize / 4;
      for (int i=0; i < brandsCount; i++) {
        if (i == 1) {
          buffer.skipBytes(4);
        }
 else         if (isCompatibleBrand(buffer.readInt())) {
          foundGoodFileType=true;
          break;
        }
      }
      if (!foundGoodFileType) {
        return false;
      }
    }
 else     if (atomDataSize != 0) {
      input.advancePeekPosition(atomDataSize);
    }
  }
  return foundGoodFileType && fragmented == isFragmented;
}
