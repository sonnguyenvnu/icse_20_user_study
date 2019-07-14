/** 
 * Calculates the final id for a LayoutOutput based on the baseId see {@link LayoutStateOutputIdCalculator#calculateLayoutOutputBaseId(LayoutOutput,int,int)} andon a sequence number. The sequence number must be guaranteed to be unique for LayoutOutputs with the same baseId.
 */
static long calculateId(long baseId,int sequence){
  if (sequence < 0 || sequence > MAX_SEQUENCE) {
    throw new IllegalArgumentException("Sequence must be non-negative and no greater than " + MAX_SEQUENCE + " actual sequence " + sequence);
  }
  return baseId | sequence;
}
