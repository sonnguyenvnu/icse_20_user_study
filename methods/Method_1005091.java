@Override public org.apache.parquet.hadoop.api.WriteSupport.WriteContext init(final Configuration configuration){
  final Map<String,String> extraMeta=new HashMap<>();
  if (null != sparkSchema) {
    extraMeta.put(ParquetReadSupport.SPARK_METADATA_KEY(),sparkSchema.json());
  }
  return new WriteContext(schema,extraMeta);
}
