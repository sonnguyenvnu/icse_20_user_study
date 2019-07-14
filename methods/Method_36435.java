/** 
 * Sets a custom value factory for the given class. Value factories are used to decode values from XML strings.
 * @param type    the object type
 * @param factory the value factory to use for the given type
 */
public void setValueFactory(Class type,XValueFactory factory){
  factories.put(type,factory);
}
