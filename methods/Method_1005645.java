/** 
 * Interns an instance, adding to the descriptor as necessary based on the given definer, name, and flags. For example, an init method has an uninitialized object of type  {@code definer}as its first argument.
 * @param descriptor {@code non-null;} the descriptor string
 * @param definer {@code non-null;} class the method is defined on
 * @param isStatic whether this is a static method
 * @param isInit whether this is an init method
 * @return {@code non-null;} the interned instance
 */
public static Prototype intern(String descriptor,Type definer,boolean isStatic,boolean isInit){
  Prototype base=intern(descriptor);
  if (isStatic) {
    return base;
  }
  if (isInit) {
    definer=definer.asUninitialized(Integer.MAX_VALUE);
  }
  return base.withFirstParameter(definer);
}
