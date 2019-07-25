/** 
 * Responsible for the writing portion of the chunking loop.  In this implementation, delegates to the { {@link #doPersist(StepContribution,Chunk)}.
 * @param contribution a {@link StepContribution}
 * @param chunk a {@link Chunk}
 * @throws Exception thrown if error occurs during the writing portion of the chunking loop.
 */
protected void persist(final StepContribution contribution,final Chunk<O> chunk) throws Exception {
  doPersist(contribution,chunk);
  contribution.incrementWriteCount(chunk.getItems().size());
}
