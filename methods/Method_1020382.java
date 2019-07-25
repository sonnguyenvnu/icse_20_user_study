/** 
 * Does the extraction.
 * @return {@code non-null;} the extracted information
 */
private LocalVariableInfo doit(){
  if (method.getRegCount() > 0) {
    for (int bi=method.getEntryBlockIndex(); bi >= 0; bi=workSet.nextSetBit(0)) {
      workSet.clear(bi);
      processBlock(bi);
    }
  }
  resultInfo.setImmutable();
  return resultInfo;
}
