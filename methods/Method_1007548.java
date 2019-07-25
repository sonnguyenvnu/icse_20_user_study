/** 
 * run the calling (static) method in the WeavingClassLoader context using the classloader that was used to load example intended to be used from main
 * @param example an object or a Class that has been loaded by the desired classloader
 * @param check if the class is already woven then just return
 * @param args to be passed to the target method
 * @return true if the trampoline was called and returned
 * @throws RuntimeException (wrapping for checked) Exceptions from the method call
 * @throws SecurityException cascaded from Class.getField
 */
public static boolean trampoline(Object example,boolean check,String... args){
  return trampoline(new Config(check,example,null).offset(2),args);
}
