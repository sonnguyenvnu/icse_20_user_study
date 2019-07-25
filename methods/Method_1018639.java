/** 
 * Revert the mask removal done while reading the code words. The bit matrix should revert to its original state.
 */
void remask(){
  if (parsedFormatInfo == null) {
    return;
  }
  DataMask dataMask=DataMask.values()[parsedFormatInfo.getDataMask()];
  int dimension=bitMatrix.getHeight();
  dataMask.unmaskBitMatrix(bitMatrix,dimension);
}
