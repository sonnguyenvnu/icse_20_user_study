@Override public Dataset<Row> apply(JavaSparkContext jsc,SparkSession sparkSession,Dataset<Row> rowDataset,TypedProperties properties){
  String transformerSQL=properties.getString(Config.TRANSFORMER_SQL);
  if (null == transformerSQL) {
    throw new IllegalArgumentException("Missing configuration : (" + Config.TRANSFORMER_SQL + ")");
  }
  String tmpTable=TMP_TABLE.concat(UUID.randomUUID().toString().replace("-","_"));
  log.info("Registering tmp table : " + tmpTable);
  rowDataset.registerTempTable(tmpTable);
  String sqlStr=transformerSQL.replaceAll(SRC_PATTERN,tmpTable);
  log.info("SQL Query for transformation : (" + sqlStr + ")");
  return sparkSession.sql(sqlStr);
}
