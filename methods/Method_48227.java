@Override public boolean has(ConfigOption option,String... umbrellaElements){
  final String key=super.getPath(option,umbrellaElements);
  return option.isLocal() && local.get(key,option.getDatatype()) != null || option.isGlobal() && global.get(key,option.getDatatype()) != null;
}
