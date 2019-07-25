/** 
 * Does the extraction.
 * @return {@code non-null;} the extracted information
 */
private LocalVariableInfo doit(){
  for (int label=method.getFirstLabel(); label >= 0; label=Bits.findFirst(workSet,0)) {
    Bits.clear(workSet,label);
    processBlock(label);
  }
  resultInfo.setImmutable();
  return resultInfo;
}
