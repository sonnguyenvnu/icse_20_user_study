/** 
 * Returns the internal name of the class corresponding to this object or array type. The internal name of a class is its fully qualified name (as returned by Class.getName(), where '.' are replaced by '/'). This method should only be used for an object or array type.
 * @return the internal name of the class corresponding to this object type.
 */
public String getInternalName(){
  return valueBuffer.substring(valueBegin,valueEnd);
}
