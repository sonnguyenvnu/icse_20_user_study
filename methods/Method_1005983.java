@Override public void save(final JdbcSession session){
  if (session.isNew()) {
    this.transactionOperations.execute(new TransactionCallbackWithoutResult(){
      @Override protected void doInTransactionWithoutResult(      TransactionStatus status){
        Map<String,String> indexes=JdbcOperationsSessionRepository.this.indexResolver.resolveIndexesFor(session);
        JdbcOperationsSessionRepository.this.jdbcOperations.update(JdbcOperationsSessionRepository.this.createSessionQuery,(ps) -> {
          ps.setString(1,session.primaryKey);
          ps.setString(2,session.getId());
          ps.setLong(3,session.getCreationTime().toEpochMilli());
          ps.setLong(4,session.getLastAccessedTime().toEpochMilli());
          ps.setInt(5,(int)session.getMaxInactiveInterval().getSeconds());
          ps.setLong(6,session.getExpiryTime().toEpochMilli());
          ps.setString(7,indexes.get(PRINCIPAL_NAME_INDEX_NAME));
        }
);
        Set<String> attributeNames=session.getAttributeNames();
        if (!attributeNames.isEmpty()) {
          insertSessionAttributes(session,new ArrayList<>(attributeNames));
        }
      }
    }
);
  }
 else {
    this.transactionOperations.execute(new TransactionCallbackWithoutResult(){
      @Override protected void doInTransactionWithoutResult(      TransactionStatus status){
        if (session.isChanged()) {
          Map<String,String> indexes=JdbcOperationsSessionRepository.this.indexResolver.resolveIndexesFor(session);
          JdbcOperationsSessionRepository.this.jdbcOperations.update(JdbcOperationsSessionRepository.this.updateSessionQuery,(ps) -> {
            ps.setString(1,session.getId());
            ps.setLong(2,session.getLastAccessedTime().toEpochMilli());
            ps.setInt(3,(int)session.getMaxInactiveInterval().getSeconds());
            ps.setLong(4,session.getExpiryTime().toEpochMilli());
            ps.setString(5,indexes.get(PRINCIPAL_NAME_INDEX_NAME));
            ps.setString(6,session.primaryKey);
          }
);
        }
        List<String> addedAttributeNames=session.delta.entrySet().stream().filter((entry) -> entry.getValue() == DeltaValue.ADDED).map(Map.Entry::getKey).collect(Collectors.toList());
        if (!addedAttributeNames.isEmpty()) {
          insertSessionAttributes(session,addedAttributeNames);
        }
        List<String> updatedAttributeNames=session.delta.entrySet().stream().filter((entry) -> entry.getValue() == DeltaValue.UPDATED).map(Map.Entry::getKey).collect(Collectors.toList());
        if (!updatedAttributeNames.isEmpty()) {
          updateSessionAttributes(session,updatedAttributeNames);
        }
        List<String> removedAttributeNames=session.delta.entrySet().stream().filter((entry) -> entry.getValue() == DeltaValue.REMOVED).map(Map.Entry::getKey).collect(Collectors.toList());
        if (!removedAttributeNames.isEmpty()) {
          deleteSessionAttributes(session,removedAttributeNames);
        }
      }
    }
);
  }
  session.clearChangeFlags();
}
