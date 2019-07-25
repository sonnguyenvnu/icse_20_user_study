static TypedTsFmtConfigFile named(String name,File file){
  return new TypedTsFmtConfigFile(TsConfigFileType.forNameIgnoreCase(name),file);
}
