/** 
 * Method that will introspect full bean properties for the purpose of building a bean deserializer
 * @param type Type of class to be introspected
 */
public BeanDescription introspect(JavaType type){
  return getClassIntrospector().forDeserialization(this,type,this);
}
