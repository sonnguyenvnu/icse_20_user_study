/** 
 * Registers a module to be returned in the  {@link #registeredModules()} string.
 * @param name The name of the module being registered.
 */
public static synchronized void registerModule(String name){
  if (registeredModules.add(name)) {
    registeredModulesString=registeredModulesString + ", " + name;
  }
}
