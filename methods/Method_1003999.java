/** 
 * {@inheritDoc}
 */
@Override public void write(final List<? extends T> items){
  if (!items.isEmpty()) {
    LOGGER.debug(() -> "Executing batch with " + items.size() + " items.");
    for (    T item : items) {
      sqlSessionTemplate.update(statementId,itemToParameterConverter.convert(item));
    }
    List<BatchResult> results=sqlSessionTemplate.flushStatements();
    if (assertUpdates) {
      if (results.size() != 1) {
        throw new InvalidDataAccessResourceUsageException("Batch execution returned invalid results. " + "Expected 1 but number of BatchResult objects returned was " + results.size());
      }
      int[] updateCounts=results.get(0).getUpdateCounts();
      for (int i=0; i < updateCounts.length; i++) {
        int value=updateCounts[i];
        if (value == 0) {
          throw new EmptyResultDataAccessException("Item " + i + " of " + updateCounts.length + " did not update any rows: [" + items.get(i) + "]",1);
        }
      }
    }
  }
}
