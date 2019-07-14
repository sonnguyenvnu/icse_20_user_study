/** 
 * Returns true if there's an instance of Processing already running. Will not return true unless this code was able to successfully contact the already running instance to have it launch sketches.
 * @param filename Path to the PDE file that was opened, null if double-clicked
 * @return true if successfully launched on the other instance
 */
static boolean alreadyRunning(String[] args){
  return Preferences.get(SERVER_PORT) != null && sendArguments(args);
}
