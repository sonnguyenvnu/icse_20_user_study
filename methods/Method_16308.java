@Override public void persistent(Object id,String targetKey,String dictId,Class type,Object value){
  if (value == null) {
    return;
  }
  Class componentType=type.isArray() ? type.getComponentType() : type;
  if (componentType.isEnum() && EnumDict.class.isAssignableFrom(componentType)) {
    List<EnumDict> dicts;
    if (type.isArray()) {
      dicts=Arrays.asList(((EnumDict[])value));
    }
 else {
      dicts=Arrays.asList(((EnumDict)value));
    }
    dictionaryInfoService.delete(targetKey,String.valueOf(id),dictId);
    dictionaryInfoService.insert(dicts.stream().map(dict -> DictionaryInfo.builder().value(String.valueOf(dict.getValue())).dictionaryId(dictId).text(dict.getText()).targetKey(targetKey).targetId(String.valueOf(id)).build()).collect(Collectors.toList()));
  }
}
