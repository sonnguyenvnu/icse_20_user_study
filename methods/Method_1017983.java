@Nonnull @Override public final T read(@Nonnull final KyloCatalogClient<T> client,@Nonnull final DataSetOptions options){
  final Option<String> catalog=options.getOption("PGDBNAME");
  final Option<String> url=options.getOption("url");
  if (catalog.isDefined() && url.isDefined() && url.get().startsWith("jdbc:postgres://")) {
    final String[] urlSplit=url.get().split("\\?",2);
    final String[] pathSplit=urlSplit[0].substring(16).split("/",2);
    if (pathSplit.length == 1 || StringUtils.equalsAny(pathSplit[1],"","/")) {
      String catalogUrl="jdbc:postgres://" + pathSplit[0] + "/" + urlEncode(catalog.get());
      if (urlSplit.length == 2) {
        catalogUrl+="?" + urlSplit[1];
      }
      options.setOption("url",catalogUrl);
    }
  }
  final DataFrameReader reader=SparkUtil.prepareDataFrameReader(getDataFrameReader(client,options),options,null);
  reader.format(JdbcRelationProvider.class.getName());
  T dataSet=load(reader);
  final String dateField=SparkUtil.getOrElse(options.getOption(DATE_FIELD_OPTION),null);
  final String highWaterMarkKey=SparkUtil.getOrElse(options.getOption(HIGH_WATER_MARK_OPTION),null);
  final Long overlap=getOverlap(options);
  if (dateField != null && highWaterMarkKey != null) {
    final JdbcHighWaterMark initialValue=createHighWaterMark(highWaterMarkKey,client);
    dataSet=filterByDateTime(dataSet,dateField,initialValue.getValue(),overlap);
    dataSet=updateHighWaterMark(dataSet,dateField,initialValue,client);
  }
 else   if (highWaterMarkKey != null) {
    log.warn("Ignoring '{}' option because '{}' option was not specified",HIGH_WATER_MARK_OPTION,DATE_FIELD_OPTION);
  }
 else   if (overlap != null) {
    log.warn("Ignoring '{}' option because '{}' and '{}' options were not specified",OVERLAP_OPTION,DATE_FIELD_OPTION,HIGH_WATER_MARK_OPTION);
  }
  return dataSet;
}
