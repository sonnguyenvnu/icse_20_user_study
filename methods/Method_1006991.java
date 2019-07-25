/** 
 * Use the technical identifier to mark the input row as processed and return unwrapped item.
 */
@Override public T process(ProcessIndicatorItemWrapper<T> wrapper) throws Exception {
  int count=jdbcTemplate.update("UPDATE BATCH_STAGING SET PROCESSED=? WHERE ID=? AND PROCESSED=?",StagingItemWriter.DONE,wrapper.getId(),StagingItemWriter.NEW);
  if (count != 1) {
    throw new OptimisticLockingFailureException("The staging record with ID=" + wrapper.getId() + " was updated concurrently when trying to mark as complete (updated " + count + " records.");
  }
  return wrapper.getItem();
}
