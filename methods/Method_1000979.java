/** 
 * Method that will introspect full bean properties for the purpose of building a bean serializer
 */
public BeanDescription introspect(JavaType type){
  return getClassIntrospector().forSerialization(this,type,this);
}
