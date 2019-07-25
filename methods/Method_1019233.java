@Override public Dataset<Row> apply(JavaSparkContext jsc,SparkSession sparkSession,Dataset<Row> rowDataset,TypedProperties properties){
  return rowDataset;
}
