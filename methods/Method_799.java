public static SerializeBeanInfo buildBeanInfo(Class<?> beanType,Map<String,String> aliasMap,PropertyNamingStrategy propertyNamingStrategy,boolean fieldBased){
  JSONType jsonType=TypeUtils.getAnnotation(beanType,JSONType.class);
  String[] orders=null;
  final int features;
  String typeName=null, typeKey=null;
  if (jsonType != null) {
    orders=jsonType.orders();
    typeName=jsonType.typeName();
    if (typeName.length() == 0) {
      typeName=null;
    }
    PropertyNamingStrategy jsonTypeNaming=jsonType.naming();
    if (jsonTypeNaming != PropertyNamingStrategy.CamelCase) {
      propertyNamingStrategy=jsonTypeNaming;
    }
    features=SerializerFeature.of(jsonType.serialzeFeatures());
    for (Class<?> supperClass=beanType.getSuperclass(); supperClass != null && supperClass != Object.class; supperClass=supperClass.getSuperclass()) {
      JSONType superJsonType=TypeUtils.getAnnotation(supperClass,JSONType.class);
      if (superJsonType == null) {
        break;
      }
      typeKey=superJsonType.typeKey();
      if (typeKey.length() != 0) {
        break;
      }
    }
    for (    Class<?> interfaceClass : beanType.getInterfaces()) {
      JSONType superJsonType=TypeUtils.getAnnotation(interfaceClass,JSONType.class);
      if (superJsonType != null) {
        typeKey=superJsonType.typeKey();
        if (typeKey.length() != 0) {
          break;
        }
      }
    }
    if (typeKey != null && typeKey.length() == 0) {
      typeKey=null;
    }
  }
 else {
    features=0;
  }
  Map<String,Field> fieldCacheMap=new HashMap<String,Field>();
  ParserConfig.parserAllFieldToCache(beanType,fieldCacheMap);
  List<FieldInfo> fieldInfoList=fieldBased ? computeGettersWithFieldBase(beanType,aliasMap,false,propertyNamingStrategy) : computeGetters(beanType,jsonType,aliasMap,fieldCacheMap,false,propertyNamingStrategy);
  FieldInfo[] fields=new FieldInfo[fieldInfoList.size()];
  fieldInfoList.toArray(fields);
  FieldInfo[] sortedFields;
  List<FieldInfo> sortedFieldList;
  if (orders != null && orders.length != 0) {
    sortedFieldList=fieldBased ? computeGettersWithFieldBase(beanType,aliasMap,true,propertyNamingStrategy) : computeGetters(beanType,jsonType,aliasMap,fieldCacheMap,true,propertyNamingStrategy);
  }
 else {
    sortedFieldList=new ArrayList<FieldInfo>(fieldInfoList);
    Collections.sort(sortedFieldList);
  }
  sortedFields=new FieldInfo[sortedFieldList.size()];
  sortedFieldList.toArray(sortedFields);
  if (Arrays.equals(sortedFields,fields)) {
    sortedFields=fields;
  }
  return new SerializeBeanInfo(beanType,jsonType,typeName,typeKey,features,fields,sortedFields);
}
