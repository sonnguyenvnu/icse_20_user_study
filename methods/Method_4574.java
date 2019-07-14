/** 
 * Does a byte by byte search to try and find the next level 1 element. This method is called if some invalid data is encountered in the parser.
 * @param input The {@link ExtractorInput} from which data has to be read.
 * @return id of the next level 1 element that has been found.
 * @throws EOFException If the end of input was encountered when searching for the next level 1element.
 * @throws IOException If an error occurs reading from the input.
 * @throws InterruptedException If the thread is interrupted.
 */
private long maybeResyncToNextLevel1Element(ExtractorInput input) throws IOException, InterruptedException {
  input.resetPeekPosition();
  while (true) {
    input.peekFully(scratch,0,MAX_ID_BYTES);
    int varintLength=VarintReader.parseUnsignedVarintLength(scratch[0]);
    if (varintLength != C.LENGTH_UNSET && varintLength <= MAX_ID_BYTES) {
      int potentialId=(int)VarintReader.assembleVarint(scratch,varintLength,false);
      if (output.isLevel1Element(potentialId)) {
        input.skipFully(varintLength);
        return potentialId;
      }
    }
    input.skipFully(1);
  }
}
