public static Map<String,Object> paths(Object javaObject,SerializeConfig config){
  Map<Object,String> values=new IdentityHashMap<Object,String>();
  Map<String,Object> paths=new HashMap<String,Object>();
  paths(values,paths,"/",javaObject,config);
  return paths;
}
