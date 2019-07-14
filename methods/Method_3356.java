/** 
 * A convenience method for parsing and automatically producing error messages.
 * @param target Either an instance or a class
 * @param args   The arguments you want to parse and populate
 * @return The list of arguments that were not consumed
 */
public static List<String> parseOrExit(Object target,String[] args){
  try {
    return parse(target,args);
  }
 catch (  IllegalArgumentException e) {
    System.err.println(e.getMessage());
    Args.usage(target);
    System.exit(1);
    throw e;
  }
}
