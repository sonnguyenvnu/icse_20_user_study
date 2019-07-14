@Override public String parseText(DictDefine dictDefine,Object value){
  return dictDefine.getItems().stream().filter(itemDefine -> itemDefine.eq(value)).map(EnumDict::getText).collect(Collectors.joining(","));
}
