/** 
 * Creates a copy of a method with a new name. This method is provided for creating a new method based on an existing method. This is a convenience method for calling {@link CtMethod#CtMethod(CtMethod,CtClass,ClassMap) this constructor}. See the description of the constructor for particular behavior of the copying.
 * @param src       the source method.
 * @param name      the name of the created method.
 * @param declaring    the class to which the created method is added.
 * @param map       the hash table associating original class nameswith substituted names. It can be <code>null</code>.
 * @see CtMethod#CtMethod(CtMethod,CtClass,ClassMap)
 */
public static CtMethod copy(CtMethod src,String name,CtClass declaring,ClassMap map) throws CannotCompileException {
  CtMethod cm=new CtMethod(src,declaring,map);
  cm.setName(name);
  return cm;
}
