@Override public void synchronize(HiveContext context,TableMergeConfig mergeConfig,String feedPartitionValue,boolean rollingSync){
  final StringBuilder sql=new StringBuilder();
  final String targetTable=HiveUtils.quoteIdentifier(mergeConfig.getTargetSchema(),mergeConfig.getTargetTable());
  final String srcTable=HiveUtils.quoteIdentifier(mergeConfig.getSourceSchema(),mergeConfig.getSourceTable());
  final List<String> selectFields=getSelectFields(context,mergeConfig);
  final String selectCols=StringUtils.join(selectFields,",");
  final PartitionSpec partitionSpec=mergeConfig.getPartionSpec();
  sql.append("insert overwrite table ").append(targetTable).append(" ");
  if (rollingSync || !partitionSpec.isNonPartitioned()) {
    sql.append(partitionSpec.toDynamicPartitionSpec()).append(" ").append("select ").append(selectCols).append(", ").append(partitionSpec.toDynamicSelectSQLSpec()).append(" ");
  }
 else {
    sql.append("select ").append(selectCols).append(" ");
  }
  sql.append("from ").append(srcTable).append(" ").append("where processing_dttm = ").append(HiveUtils.quoteString(feedPartitionValue)).append(" ");
  if (rollingSync) {
    List<PartitionBatch> batches=createPartitionBatches(context,mergeConfig,feedPartitionValue);
    sql.append(" and (").append(targetPartitionsWhereClause(batches,true)).append(")");
  }
  executeSQL(context,sql.toString());
}
