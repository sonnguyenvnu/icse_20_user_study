/** 
 * ?????? ??? 5 ? 10 ?? <p> ?? 0
 */
public Word2VecTrainer useNegativeSamples(int negativeSamples){
  Preconditions.checkArgument(negativeSamples >= 0,"Value must be non-negative");
  this.negativeSamples=negativeSamples;
  return this;
}
