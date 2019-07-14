/** 
 * @param args String[]
 * @param name String
 * @return boolean
 */
private static boolean findBooleanSwitch(String[] args,String name){
  for (int i=0; i < args.length; i++) {
    if (args[i].equals(name)) {
      return true;
    }
  }
  return false;
}
