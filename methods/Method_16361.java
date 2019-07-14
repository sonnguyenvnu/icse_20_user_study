protected RDBTableMetaData buildTable(RDBDatabase database,DynamicFormEntity form,List<DynamicFormColumnEntity> columns){
  RDBTableMetaData metaData=new RDBTableMetaData();
  metaData.setComment(form.getDescribe());
  metaData.setName(form.getDatabaseTableName());
  if (null != form.getProperties()) {
    metaData.setProperties(form.getProperties());
  }
  metaData.setProperty("version",form.getVersion());
  metaData.setProperty("formId",form.getId());
  metaData.setAlias(form.getAlias());
  metaData.setCorrelations(buildCorrelations(form.getCorrelations()));
  metaData.setDatabaseMetaData(database.getMeta());
  buildTrigger(form.getTriggers()).forEach(metaData::on);
  columns.forEach(column -> {
    RDBColumnMetaData columnMeta=new RDBColumnMetaData();
    columnMeta.setName(column.getColumnName());
    columnMeta.setAlias(column.getAlias());
    columnMeta.setComment(column.getDescribe());
    columnMeta.setLength(column.getLength() == null ? 0 : column.getLength());
    columnMeta.setPrecision(column.getPrecision() == null ? 0 : column.getPrecision());
    columnMeta.setScale(column.getScale() == null ? 0 : column.getScale());
    columnMeta.setJdbcType(JDBCType.valueOf(column.getJdbcType()));
    columnMeta.setJavaType(getJavaType(column.getJavaType()));
    columnMeta.setProperties(column.getProperties() == null ? new HashMap<>() : column.getProperties());
    if (!CollectionUtils.isEmpty(column.getValidator())) {
      columnMeta.setValidator(new HashSet<>(column.getValidator()));
    }
    if (StringUtils.isEmpty(column.getDataType())) {
      Dialect dialect=database.getMeta().getDialect();
      columnMeta.setDataType(dialect.buildDataType(columnMeta));
    }
 else {
      columnMeta.setDataType(column.getDataType());
    }
    columnMeta.setValueConverter(initColumnValueConvert(columnMeta.getJdbcType(),columnMeta.getJavaType()));
    if (optionalConvertBuilder != null && null != column.getDictConfig()) {
      try {
        DictConfig config=JSON.parseObject(column.getDictConfig(),DictConfig.class);
        config.setColumn(columnMeta);
        columnMeta.setOptionConverter(optionalConvertBuilder.build(config));
        ValueConverter converter=optionalConvertBuilder.buildValueConverter(config);
        if (null != converter) {
          columnMeta.setValueConverter(converter);
        }
      }
 catch (      Exception e) {
        logger.warn("?????????",e);
      }
    }
    customColumnSetting(database,form,metaData,column,columnMeta);
    metaData.addColumn(columnMeta);
  }
);
  if (objectWrapperFactory != null) {
    metaData.setObjectWrapper(objectWrapperFactory.createObjectWrapper(metaData));
  }
  metaData.setValidator(validatorFactory.createValidator(metaData));
  customTableSetting(database,form,metaData);
  if (metaData.getColumns().stream().noneMatch(RDBColumnMetaData::isPrimaryKey) && metaData.findColumn("id") == null) {
    RDBColumnMetaData primaryKey=createPrimaryKeyColumn();
    Dialect dialect=database.getMeta().getDialect();
    primaryKey.setDataType(dialect.buildDataType(primaryKey));
    metaData.addColumn(primaryKey);
  }
  return metaData;
}
