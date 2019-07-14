/** 
 * Generates a qname for a parameter of a function or method. There is not enough context for  {@link #extendPath} to differentiateparams from locals, so callers must use this method when the name is known to be a parameter name.
 */
public String extendPathForParam(String name){
  if (path.isEmpty()) {
    throw new IllegalStateException("Not inside a function");
  }
  StringBuilder sb=new StringBuilder();
  sb.append(path).append("@").append(name);
  return sb.toString();
}
