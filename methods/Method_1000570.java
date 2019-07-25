/** 
 * ??????????, ???set??
 */
public static JsonEntityField eval(Mirror<?> mirror,String name,Method getter,Method setter){
  JsonEntityField jef=new JsonEntityField();
  jef.declaringClass=mirror.getType();
  jef.setGenericType(getter.getGenericReturnType());
  jef.name=name;
  jef.ejecting=new EjectByGetter(getter);
  jef.injecting=new InjectBySetter(setter);
  jef.mirror=Mirror.me(getter.getReturnType());
  return jef;
}
