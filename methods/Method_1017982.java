@Override public final void write(@Nonnull final KyloCatalogClient<T> client,@Nonnull final DataSetOptions options,@Nonnull final T dataSet){
  final String tableName=DataSetUtil.getOptionOrThrow(options,KyloCatalogConstants.PATH_OPTION,"Table name must be defined as path");
  addJars(client,options.getJars());
  final DataFrameWriter writer=SparkUtil.prepareDataFrameWriter(getDataFrameWriter(dataSet,options),options,null);
  writer.insertInto(tableName);
}
