@Override @SuppressWarnings("all") public Object getDictEnum(Object id,String targetKey,String dictId,Class type){
  List<DictionaryInfo> infos=dictionaryInfoService.select(targetKey,String.valueOf(id),dictId);
  Class componentType=type.isArray() ? type.getComponentType() : type;
  if (componentType.isEnum() && EnumDict.class.isAssignableFrom(componentType)) {
    Stream stream=infos.stream().map(DictionaryInfo::getValue).map(val -> EnumDict.find(componentType,val).orElse(null)).filter(Objects::nonNull);
    if (type.isArray()) {
      return stream.toArray(len -> Array.newInstance(componentType,len));
    }
 else {
      return stream.findFirst().orElse(null);
    }
  }
  return null;
}
