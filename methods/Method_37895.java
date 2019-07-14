/** 
 * Returns the class of the immediate subclass of the given parent class for the given object instance; or null if such immediate subclass cannot be uniquely identified for the given object instance.
 */
public static Class<?> childClassOf(final Class<?> parentClass,final Object instance){
  if (instance == null || instance == Object.class) {
    return null;
  }
  if (parentClass != null) {
    if (parentClass.isInterface()) {
      return null;
    }
  }
  Class<?> childClass=instance.getClass();
  while (true) {
    Class<?> parent=childClass.getSuperclass();
    if (parent == parentClass) {
      return childClass;
    }
    if (parent == null) {
      return null;
    }
    childClass=parent;
  }
}
