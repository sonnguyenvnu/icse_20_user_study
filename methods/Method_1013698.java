@Override protected String[] convert(String value){
  return StringUtil.splitRemoveEmpty("\\s*,\\s*",value);
}
