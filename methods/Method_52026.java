/** 
 * Returns the binary name of the type identified by this qualified name. The binary name can be used to load a  {@link Class} using a {@link ClassLoader}. Contrary to this method,  {@link #toString()} is not guaranteed to returna binary name. For most purposes, you should avoid using this method directly and use  {@link #getType()} instead. Just don't build adependency on the toString if you want a binary name.
 * @return The binary name of the type identified by this qualified name
 */
public String getBinaryName(){
  return toString();
}
