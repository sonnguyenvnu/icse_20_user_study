/** 
 * Sets the major version and the name of the class to which this symbol table belongs. Also adds the class name to the constant pool.
 * @param majorVersion a major ClassFile version number.
 * @param className an internal class name.
 * @return the constant pool index of a new or already existing Symbol with the given class name.
 */
int setMajorVersionAndClassName(final int majorVersion,final String className){
  this.majorVersion=majorVersion;
  this.className=className;
  return addConstantClass(className).index;
}
