private static Map<Class<?>,Class<?>> buildConvertableTypesMap(){
  Map<Class<?>,Class<?>> map=new HashMap<Class<?>,Class<?>>();
  putSymmetrically(map,boolean.class,Boolean.class);
  putSymmetrically(map,byte.class,Byte.class);
  putSymmetrically(map,short.class,Short.class);
  putSymmetrically(map,char.class,Character.class);
  putSymmetrically(map,int.class,Integer.class);
  putSymmetrically(map,long.class,Long.class);
  putSymmetrically(map,float.class,Float.class);
  putSymmetrically(map,double.class,Double.class);
  return Collections.unmodifiableMap(map);
}
