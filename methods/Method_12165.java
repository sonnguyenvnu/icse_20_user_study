/** 
 * ?json????????
 * @param jsonData json??
 * @param clazz ????object??
 * @return
 */
public static <T>T jsonToPojo(String jsonData,Class<T> beanType){
  try {
    T t=MAPPER.readValue(jsonData,beanType);
    return t;
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return null;
}
