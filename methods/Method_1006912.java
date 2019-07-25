/** 
 * Sets the chunk size or commit interval for this step. This is the maximum number of items that will be read before processing starts in a single transaction. Not compatible with  {@link #completionPolicy}.
 * @param chunkSize the chunk size (a.k.a commit interval)
 * @return this for fluent chaining
 */
public SimpleStepBuilder<I,O> chunk(int chunkSize){
  Assert.state(completionPolicy == null || chunkSize == 0,"You must specify either a chunkCompletionPolicy or a commitInterval but not both.");
  this.chunkSize=chunkSize;
  return this;
}
