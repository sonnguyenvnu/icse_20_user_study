/** 
 * Sets a completion policy for the chunk processing. Items are read until this policy determines that a chunk is complete, giving more control than with just the  {@link #chunk(int) chunk size} (or commit interval).
 * @param completionPolicy a completion policy for the chunk
 * @return this for fluent chaining
 */
public SimpleStepBuilder<I,O> chunk(CompletionPolicy completionPolicy){
  Assert.state(chunkSize == 0 || completionPolicy == null,"You must specify either a chunkCompletionPolicy or a commitInterval but not both.");
  this.completionPolicy=completionPolicy;
  return this;
}
