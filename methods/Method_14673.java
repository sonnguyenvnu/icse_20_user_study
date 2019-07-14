/** 
 * Register a single command.
 * @param path path for command
 * @param commandObject object implementing the command
 * @return true if command was loaded and registered successfully
 */
protected boolean registerOneCommand(String path,Command commandObject){
  if (commands.containsKey(path)) {
    return false;
  }
  commandObject.init(this);
  commands.put(path,commandObject);
  return true;
}
