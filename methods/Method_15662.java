protected DataAccessConfigConvert createConfig(String supportType,BiFunction<String,String,? extends DataAccessConfig> function){
  return new DataAccessConfigConvert(){
    @Override public boolean isSupport(    String type,    String action,    String config){
      return supportType.equals(type);
    }
    @Override public DataAccessConfig convert(    String type,    String action,    String config){
      DataAccessConfig conf=function.apply(action,config);
      if (conf instanceof AbstractDataAccessConfig) {
        ((AbstractDataAccessConfig)conf).setAction(action);
      }
      return conf;
    }
  }
;
}
