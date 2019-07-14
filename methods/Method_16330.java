@Override public OptionConverter build(DictConfig dictConfig){
  JSONObject conf=new JSONObject(dictConfig.getConfig());
  String dictId=conf.getString("dictId");
  String fieldName=dictConfig.getToField();
  String sppliter=conf.getString("spliter");
  String writeObject=conf.getString("writeObject");
  EnumDictOptionConverter<EnumDict<Object>> converter=new EnumDictOptionConverter<>(() -> dictDefineRepository.getDefine(dictId).getItems(),fieldName);
  converter.setWriteObject(!"false".equalsIgnoreCase(writeObject));
  if (!StringUtils.isEmpty(sppliter)) {
    converter.setSplitter(str -> Arrays.asList(str.split(sppliter)));
  }
  return converter;
}
