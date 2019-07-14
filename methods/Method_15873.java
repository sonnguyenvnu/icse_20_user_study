protected RDBTableMetaData createMeta(String tableName,String resultMapId){
  RDBDatabaseMetaData active=getActiveDatabase();
  String cacheKey=tableName.concat("-").concat(resultMapId);
  Map<String,RDBTableMetaData> cache=metaCache.computeIfAbsent(active,k -> new ConcurrentHashMap<>());
  RDBTableMetaData cached=cache.get(cacheKey);
  if (cached != null) {
    return cached;
  }
  RDBTableMetaData rdbTableMetaData=new RDBTableMetaData(){
    @Override public String getName(){
      return getRealTableName(tableName);
    }
  }
;
  ResultMap resultMaps=MybatisUtils.getResultMap(resultMapId);
  rdbTableMetaData.setName(tableName);
  rdbTableMetaData.setDatabaseMetaData(active);
  List<ResultMapping> resultMappings=new ArrayList<>(resultMaps.getResultMappings());
  resultMappings.addAll(resultMaps.getIdResultMappings());
  for (  ResultMapping resultMapping : resultMappings) {
    if (resultMapping.getNestedQueryId() == null) {
      RDBColumnMetaData column=new RDBColumnMetaData();
      column.setJdbcType(JDBCType.valueOf(resultMapping.getJdbcType().name()));
      column.setName(resultMapping.getColumn());
      if (resultMapping.getTypeHandler() != null) {
        column.setProperty("typeHandler",resultMapping.getTypeHandler().getClass().getName());
      }
      if (!StringUtils.isNullOrEmpty(resultMapping.getProperty())) {
        column.setAlias(resultMapping.getProperty());
      }
      column.setJavaType(resultMapping.getJavaType());
      column.setProperty("resultMapping",resultMapping);
      if (column.getJdbcType() == JDBCType.DATE || column.getJdbcType() == JDBCType.TIMESTAMP) {
        ValueConverter dateConvert=new DateTimeConverter("yyyy-MM-dd HH:mm:ss",column.getJavaType()){
          @Override public Object getData(          Object value){
            if (value instanceof Number) {
              return new Date(((Number)value).longValue());
            }
            return super.getData(value);
          }
        }
;
        column.setValueConverter(dateConvert);
      }
 else       if (column.getJavaType() == boolean.class || column.getJavaType() == Boolean.class) {
        column.setValueConverter(new BooleanValueConverter(column.getJdbcType()));
      }
 else       if (TypeUtils.isNumberType(column)) {
        column.setValueConverter(new NumberValueConverter(column.getJavaType()));
      }
      rdbTableMetaData.addColumn(column);
    }
  }
  if (useJpa) {
    Class type=entityFactory == null ? resultMaps.getType() : entityFactory.getInstanceType(resultMaps.getType());
    RDBTableMetaData parseResult=JpaAnnotationParser.parseMetaDataFromEntity(type);
    if (parseResult != null) {
      for (      RDBColumnMetaData columnMetaData : parseResult.getColumns()) {
        if (rdbTableMetaData.findColumn(columnMetaData.getName()) == null) {
          columnMetaData=columnMetaData.clone();
          columnMetaData.setProperty("fromJpa",true);
          rdbTableMetaData.addColumn(columnMetaData);
        }
      }
    }
  }
  cache.put(cacheKey,rdbTableMetaData);
  return rdbTableMetaData;
}
