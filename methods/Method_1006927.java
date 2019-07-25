@SuppressWarnings({"unchecked","rawtypes"}) @Override public void write(final List<? extends T> items) throws Exception {
  if (!items.isEmpty()) {
    if (logger.isDebugEnabled()) {
      logger.debug("Executing batch with " + items.size() + " items.");
    }
    int[] updateCounts;
    if (usingNamedParameters) {
      if (items.get(0) instanceof Map && this.itemSqlParameterSourceProvider == null) {
        updateCounts=namedParameterJdbcTemplate.batchUpdate(sql,items.toArray(new Map[items.size()]));
      }
 else {
        SqlParameterSource[] batchArgs=new SqlParameterSource[items.size()];
        int i=0;
        for (        T item : items) {
          batchArgs[i++]=itemSqlParameterSourceProvider.createSqlParameterSource(item);
        }
        updateCounts=namedParameterJdbcTemplate.batchUpdate(sql,batchArgs);
      }
    }
 else {
      updateCounts=namedParameterJdbcTemplate.getJdbcOperations().execute(sql,new PreparedStatementCallback<int[]>(){
        @Override public int[] doInPreparedStatement(        PreparedStatement ps) throws SQLException, DataAccessException {
          for (          T item : items) {
            itemPreparedStatementSetter.setValues(item,ps);
            ps.addBatch();
          }
          return ps.executeBatch();
        }
      }
);
    }
    if (assertUpdates) {
      for (int i=0; i < updateCounts.length; i++) {
        int value=updateCounts[i];
        if (value == 0) {
          throw new EmptyResultDataAccessException("Item " + i + " of " + updateCounts.length + " did not update any rows: [" + items.get(i) + "]",1);
        }
      }
    }
  }
}
