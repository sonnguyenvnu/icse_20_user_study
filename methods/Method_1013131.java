/** 
 * Returns whether the input proofPosition is contained inside this instance. More precisely, returns this.offset <= proofPosition.getOffset() && this.offset + this.length >= proofPosition.getOffset() + proofPosition.getLength()
 * @param proofPosition
 * @return
 */
public boolean contains(TLAProofPosition proofPosition){
  return this.offset <= proofPosition.getOffset() && this.offset + this.length >= proofPosition.getOffset() + proofPosition.getLength();
}
