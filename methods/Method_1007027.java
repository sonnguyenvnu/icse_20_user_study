/** 
 * Constructs a class from the given argument.
 * @param c The argument to construct this class with.
 * @return A class from the given argument.
 */
public static <T>Class<T> clas(final java.lang.Class<T> c){
  return new Class<>(c);
}
