@Override public String parseValue(DictDefine dictDefine,String text){
  return dictDefine.getItems().stream().filter(itemDefine -> itemDefine.eq(text)).map(EnumDict::getValue).map(String::valueOf).findFirst().orElse(text);
}
