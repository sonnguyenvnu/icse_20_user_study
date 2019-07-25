/** 
 * Define a class of the give name via its bytecode.
 * @param name Name of the class to define.
 * @param bytecode Bytecode of the class.
 * @param argTypes Constructor type arguments.
 * @param argValues Constructor argument values.
 * @return Instance of the class.
 * @throws ClassNotFoundException Not thrown, only there to satisfy compiler enforced exception.
 * @throws NoSuchMethodException Thrown if a constructor defined by the given type array does not exist.
 * @throws ReflectiveOperationException Thrown if the constructor could not be called.
 */
public static Object create(String name,byte[] bytecode,Class<?>[] argTypes,Object[] argValues) throws ClassNotFoundException, NoSuchMethodException, ReflectiveOperationException {
  Class<?> c=new ClassDefiner(name,bytecode).findClass(name);
  Constructor<?> con=c.getDeclaredConstructor(argTypes);
  return con.newInstance(argValues);
}
