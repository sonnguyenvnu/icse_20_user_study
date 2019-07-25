public static MapSerializer construct(Set<String> ignoredEntries,JavaType mapType,boolean staticValueType,TypeSerializer vts,JsonSerializer<Object> keySerializer,JsonSerializer<Object> valueSerializer,Object filterId){
  JavaType keyType, valueType;
  if (mapType == null) {
    keyType=valueType=UNSPECIFIED_TYPE;
  }
 else {
    keyType=mapType.getKeyType();
    valueType=mapType.getContentType();
  }
  if (!staticValueType) {
    staticValueType=(valueType != null && valueType.isFinal());
  }
 else {
    if (valueType.getRawClass() == Object.class) {
      staticValueType=false;
    }
  }
  MapSerializer ser=new MapSerializer(ignoredEntries,keyType,valueType,staticValueType,vts,keySerializer,valueSerializer);
  if (filterId != null) {
    ser=ser.withFilterId(filterId);
  }
  return ser;
}
