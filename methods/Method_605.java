/** 
 * @since 1.2.25
 */
public void initJavaBeanDeserializers(Class<?>... classes){
  if (classes == null) {
    return;
  }
  for (  Class<?> type : classes) {
    if (type == null) {
      continue;
    }
    ObjectDeserializer deserializer=createJavaBeanDeserializer(type,type);
    putDeserializer(type,deserializer);
  }
}
