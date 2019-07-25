/** 
 * Applies a transformation of a given type with some function to a value.
 * @param type the transformation type, e.g. REGEX or MAP
 * @param function the function to call, this value depends on the transformation type
 * @param value the value to apply the transformation to
 * @return the transformed value or the original one, if there was no service registered for thegiven type or a transformation exception occurred.
 */
public static @Nullable String transform(String type,String function,String value){
  Logger logger=LoggerFactory.getLogger(Transformation.class);
  String result;
  try {
    result=trans(type,function,value);
  }
 catch (  TransformationException e) {
    logger.debug("Error executing the transformation '{}': {}",type,e.getMessage());
    result=value;
  }
  return result;
}
