/** 
 * Resolves target type: either using  {@link #type() provided type}or type of the  {@link #value() value}.
 */
public Class resolveType(){
  if (type != null) {
    return type;
  }
  return value.getClass();
}
