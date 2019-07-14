/** 
 * ???????? <p> ??  {@link Runtime#availableProcessors()}
 */
public Word2VecTrainer useNumThreads(int numThreads){
  Preconditions.checkArgument(numThreads > 0,"Value must be positive");
  this.numThreads=numThreads;
  return this;
}
