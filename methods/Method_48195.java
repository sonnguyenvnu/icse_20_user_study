@Override public boolean has(ConfigOption option,String... umbrellaElements){
  verifyOption(option);
  return config.get(super.getPath(option,umbrellaElements),option.getDatatype()) != null;
}
