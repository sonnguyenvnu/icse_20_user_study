public static TypeNameIdResolver construct(MapperConfig<?> config,JavaType baseType,Collection<NamedType> subtypes,boolean forSer,boolean forDeser){
  if (forSer == forDeser)   throw new IllegalArgumentException();
  Map<String,String> typeToId=null;
  Map<String,JavaType> idToType=null;
  if (forSer) {
    typeToId=new HashMap<String,String>();
  }
  if (forDeser) {
    idToType=new HashMap<String,JavaType>();
    typeToId=new TreeMap<String,String>();
  }
  if (subtypes != null) {
    for (    NamedType t : subtypes) {
      Class<?> cls=t.getType();
      String id=t.hasName() ? t.getName() : _defaultTypeId(cls);
      if (forSer) {
        typeToId.put(cls.getName(),id);
      }
      if (forDeser) {
        JavaType prev=idToType.get(id);
        if (prev != null) {
          if (cls.isAssignableFrom(prev.getRawClass())) {
            continue;
          }
        }
        idToType.put(id,config.constructType(cls));
      }
    }
  }
  return new TypeNameIdResolver(config,baseType,typeToId,idToType);
}
