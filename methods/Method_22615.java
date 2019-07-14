/** 
 * Returns the appropriate file extension to use for auxilliary source files in a sketch. For example, in a Java-mode sketch, auxilliary files should be name "Foo.java"; in Python mode, they should be named "foo.py". <p>Modes that do not override this function will get the default behavior of returning the default extension.
 */
public String getModuleExtension(){
  return getDefaultExtension();
}
