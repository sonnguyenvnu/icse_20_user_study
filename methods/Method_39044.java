/** 
 * Lookups action runtime config for given action class and method string (aka 'action string'). The action string has the following format: <code>className#methodName</code>.
 * @see ActionRuntime#createActionString()
 */
public ActionRuntime lookup(final String actionString){
  return runtimes.get(actionString);
}
