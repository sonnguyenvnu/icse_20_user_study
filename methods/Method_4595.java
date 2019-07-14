/** 
 * Reads an EBML variable-length integer (varint) from an  {@link ExtractorInput} such thatreading can be resumed later if an error occurs having read only some of it. <p> If an value is successfully read, then the reader will automatically reset itself ready to read another value. <p> If an  {@link IOException} or {@link InterruptedException} is throw, the read can be resumedlater by calling this method again, passing an  {@link ExtractorInput} providing data startingwhere the previous one left off.
 * @param input The {@link ExtractorInput} from which the integer should be read.
 * @param allowEndOfInput True if encountering the end of the input having read no data isallowed, and should result in  {@link C#RESULT_END_OF_INPUT} being returned. False if itshould be considered an error, causing an  {@link EOFException} to be thrown.
 * @param removeLengthMask Removes the variable-length integer length mask from the value.
 * @param maximumAllowedLength Maximum allowed length of the variable integer to be read.
 * @return The read value, or {@link C#RESULT_END_OF_INPUT} if {@code allowEndOfStream} is trueand the end of the input was encountered, or  {@link C#RESULT_MAX_LENGTH_EXCEEDED} if thelength of the varint exceeded maximumAllowedLength.
 * @throws IOException If an error occurs reading from the input.
 * @throws InterruptedException If the thread is interrupted.
 */
public long readUnsignedVarint(ExtractorInput input,boolean allowEndOfInput,boolean removeLengthMask,int maximumAllowedLength) throws IOException, InterruptedException {
  if (state == STATE_BEGIN_READING) {
    if (!input.readFully(scratch,0,1,allowEndOfInput)) {
      return C.RESULT_END_OF_INPUT;
    }
    int firstByte=scratch[0] & 0xFF;
    length=parseUnsignedVarintLength(firstByte);
    if (length == C.LENGTH_UNSET) {
      throw new IllegalStateException("No valid varint length mask found");
    }
    state=STATE_READ_CONTENTS;
  }
  if (length > maximumAllowedLength) {
    state=STATE_BEGIN_READING;
    return C.RESULT_MAX_LENGTH_EXCEEDED;
  }
  if (length != 1) {
    input.readFully(scratch,1,length - 1);
  }
  state=STATE_BEGIN_READING;
  return assembleVarint(scratch,length,removeLengthMask);
}
