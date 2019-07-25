public static JsonEntityField eval(Mirror<?> mirror,String name,Type type,Ejecting ejecting,Injecting injecting){
  JsonEntityField jef=new JsonEntityField();
  jef.genericType=mirror.getType();
  jef.setGenericType(type);
  jef.name=name;
  jef.ejecting=ejecting;
  jef.injecting=injecting;
  jef.mirror=Mirror.me(type);
  return jef;
}
