/** 
 * Register a single command.
 * @param module the module the command belongs to
 * @param name command verb for command
 * @param commandObject object implementing the command
 * @return true if command was loaded and registered successfully
 */
protected boolean registerOneCommand(ButterflyModule module,String name,Command commandObject){
  return registerOneCommand(module.getName() + "/" + name,commandObject);
}
