@Override public ValueConverter buildValueConverter(DictConfig dictConfig){
  JSONObject conf=new JSONObject(dictConfig.getConfig());
  String dictId=conf.getString("dictId");
  boolean multi=!"false".equalsIgnoreCase(conf.getString("multi"));
  Supplier<List<EnumDict<Object>>> supplier=() -> dictDefineRepository.getDefine(dictId).getItems();
  EnumDictValueConverter<EnumDict<Object>> converter=new EnumDictValueConverter<>(supplier);
  converter.setMulti(multi);
  RDBColumnMetaData column=dictConfig.getColumn();
  if (multi && (column.getJdbcType() == JDBCType.NUMERIC || column.getJdbcType() == JDBCType.BIGINT)) {
    if (supplier.get().size() < 64) {
      column.setProperty(DictInTermTypeMapper.USE_DICT_MASK_FLAG,true);
      converter.setDataToMask(true);
    }
 else {
      throw new UnsupportedOperationException("???????,????????????64?,?????!");
    }
  }
  return converter;
}
