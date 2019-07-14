/** 
 * Returns the  {@link Type} values corresponding to the argument types of the given method.
 * @param method a method.
 * @return the {@link Type} values corresponding to the argument types of the given method.
 */
public static Type[] getArgumentTypes(final Method method){
  Class<?>[] classes=method.getParameterTypes();
  Type[] types=new Type[classes.length];
  for (int i=classes.length - 1; i >= 0; --i) {
    types[i]=getType(classes[i]);
  }
  return types;
}
