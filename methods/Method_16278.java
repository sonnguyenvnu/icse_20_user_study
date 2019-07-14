@Bean public BeanPostProcessor tableMetaDataAutoParserRegister(){
  return new BeanPostProcessor(){
    @Override public Object postProcessBeforeInitialization(    Object o,    String s) throws BeansException {
      return o;
    }
    @Override @SuppressWarnings("unchecked") public Object postProcessAfterInitialization(    Object o,    String s) throws BeansException {
      if (o instanceof MetaDataParserSupplier) {
        MetaDataParserSupplier<? extends TableMetaDataParser> supplier=((MetaDataParserSupplier)o);
        for (        DatabaseType databaseType : DatabaseType.values()) {
          if (supplier.isSupport(databaseType)) {
            metaDataParserRegister.registerMetaDataParser(databaseType,ObjectMetadata.ObjectType.TABLE,supplier.get());
          }
        }
      }
      return o;
    }
  }
;
}
