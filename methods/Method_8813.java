public static Map<String,Shader> setup(){
  Map<String,Shader> result=new HashMap<>();
  for (  Map.Entry<String,Map<String,Object>> entry : AVAILBALBE_SHADERS.entrySet()) {
    Map<String,Object> value=entry.getValue();
    String vertex=(String)value.get(VERTEX);
    String fragment=(String)value.get(FRAGMENT);
    String[] attributes=(String[])value.get(ATTRIBUTES);
    String[] uniforms=(String[])value.get(UNIFORMS);
    Shader shader=new Shader(vertex,fragment,attributes,uniforms);
    result.put(entry.getKey(),shader);
  }
  return Collections.unmodifiableMap(result);
}
