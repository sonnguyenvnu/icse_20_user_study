/** 
 * Encodes the input byte sequence into the output char sequence.  Before calling this method, ensure that the output array has sufficient capacity by calling  {@link #getEncodedLength(byte[],int,int)}.
 * @param inputArray byte sequence to be encoded
 * @param inputOffset initial offset into inputArray
 * @param inputLength number of bytes in inputArray
 * @param outputArray char sequence to store encoded result
 * @param outputOffset initial offset into outputArray
 * @param outputLength length of output, must be getEncodedLength
 */
public static void encode(byte[] inputArray,int inputOffset,int inputLength,char[] outputArray,int outputOffset,int outputLength){
  assert (outputLength == getEncodedLength(inputArray,inputOffset,inputLength));
  if (inputLength > 0) {
    int inputByteNum=inputOffset;
    int caseNum=0;
    int outputCharNum=outputOffset;
    CodingCase codingCase;
    for (; inputByteNum + CODING_CASES[caseNum].numBytes <= inputLength; ++outputCharNum) {
      codingCase=CODING_CASES[caseNum];
      if (2 == codingCase.numBytes) {
        outputArray[outputCharNum]=(char)(((inputArray[inputByteNum] & 0xFF) << codingCase.initialShift) + (((inputArray[inputByteNum + 1] & 0xFF) >>> codingCase.finalShift) & codingCase.finalMask) & (short)0x7FFF);
      }
 else {
        outputArray[outputCharNum]=(char)(((inputArray[inputByteNum] & 0xFF) << codingCase.initialShift) + ((inputArray[inputByteNum + 1] & 0xFF) << codingCase.middleShift) + (((inputArray[inputByteNum + 2] & 0xFF) >>> codingCase.finalShift) & codingCase.finalMask) & (short)0x7FFF);
      }
      inputByteNum+=codingCase.advanceBytes;
      if (++caseNum == CODING_CASES.length) {
        caseNum=0;
      }
    }
    codingCase=CODING_CASES[caseNum];
    if (inputByteNum + 1 < inputLength) {
      outputArray[outputCharNum++]=(char)((((inputArray[inputByteNum] & 0xFF) << codingCase.initialShift) + ((inputArray[inputByteNum + 1] & 0xFF) << codingCase.middleShift)) & (short)0x7FFF);
      outputArray[outputCharNum++]=(char)1;
    }
 else     if (inputByteNum < inputLength) {
      outputArray[outputCharNum++]=(char)(((inputArray[inputByteNum] & 0xFF) << codingCase.initialShift) & (short)0x7FFF);
      outputArray[outputCharNum++]=caseNum == 0 ? (char)1 : (char)0;
    }
 else {
      outputArray[outputCharNum++]=(char)1;
    }
  }
}
