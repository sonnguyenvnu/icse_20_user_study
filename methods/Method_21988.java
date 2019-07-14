private String getTableTimeField(final String tableName){
  String result="";
  if (TABLE_JOB_EXECUTION_LOG.equals(tableName)) {
    result="start_time";
  }
 else   if (TABLE_JOB_STATUS_TRACE_LOG.equals(tableName)) {
    result="creation_time";
  }
  return result;
}
