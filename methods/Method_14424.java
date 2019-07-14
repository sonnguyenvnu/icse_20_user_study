public void flushJoinsInvolvingProjectColumn(long projectID,String columnName){
synchronized (_joins) {
    for (Iterator<Entry<String,ProjectJoin>> it=_joins.entrySet().iterator(); it.hasNext(); ) {
      Entry<String,ProjectJoin> entry=it.next();
      ProjectJoin join=entry.getValue();
      if (join.fromProjectID == projectID && join.fromProjectColumnName.equals(columnName) || join.toProjectID == projectID && join.toProjectColumnName.equals(columnName)) {
        it.remove();
      }
    }
  }
}
