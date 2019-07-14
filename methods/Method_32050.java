/** 
 * Gets a debugging string version of this converter.
 * @return a debugging string
 */
public String toString(){
  return "Converter[" + (getSupportedType() == null ? "null" : getSupportedType().getName()) + "]";
}
