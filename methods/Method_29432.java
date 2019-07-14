/** 
 * ?json?????
 * @param < T >
 * @param content
 * @param valueType
 * @return
 */
public static <T>T fromJson(String content,Class<T> valueType){
  if (content == null) {
    return null;
  }
  try {
    return mapper.readValue(content,valueType);
  }
 catch (  IOException e) {
    logger.error("parse content=" + content + " error!",e);
  }
  return null;
}
