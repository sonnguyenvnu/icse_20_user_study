/** 
 * Define a class of the give name via its bytecode.
 * @param name Name of the class to define.
 * @param bytecode Bytecode of the class.
 * @return Instance of the class <i>(Default constructor)</i>
 * @throws ClassNotFoundException Not thrown, only there to satisfy compiler enforced exception.
 * @throws NoSuchMethodException Thrown if there is no default constructor in the class.
 * @throws ReflectiveOperationException Thrown if the constructor could not be called.
 */
public static Object create(String name,byte[] bytecode) throws ClassNotFoundException, NoSuchMethodException, ReflectiveOperationException {
  Class<?> c=new ClassDefiner(name,bytecode).findClass(name);
  Constructor<?> con=c.getDeclaredConstructor();
  return con.newInstance();
}
