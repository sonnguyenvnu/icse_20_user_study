/** 
 * Creates table reference name from entity type. Always appends an underscore to reference name in order to circumvent SQL compatibility issues when entity class name equals to a reserved word.
 */
protected static String createTableRefName(final Object entity){
  Class type=entity.getClass();
  type=(type == Class.class ? (Class)entity : type);
  return (type.getSimpleName() + '_');
}
