/** 
 * ?json?????pojo??list <p>Title: jsonToList</p> <p>Description: </p>
 * @param jsonData
 * @param beanType
 * @return
 */
public static <T>List<T> jsonToList(String jsonData,Class<T> beanType){
  JavaType javaType=MAPPER.getTypeFactory().constructParametricType(List.class,beanType);
  try {
    List<T> list=MAPPER.readValue(jsonData,javaType);
    return list;
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return null;
}
