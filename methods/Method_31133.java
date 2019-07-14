/** 
 * Determine the operations Flyway should execute.
 * @param args The command-line arguments passed in.
 * @return The operations. An empty list if none.
 */
private static List<String> determineOperations(String[] args){
  List<String> operations=new ArrayList<>();
  for (  String arg : args) {
    if (!arg.startsWith("-")) {
      operations.add(arg);
    }
  }
  return operations;
}
