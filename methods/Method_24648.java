/** 
 * Print info about a ReferenceType. Prints class name, source file name, lists methods.
 * @param rt the reference type to print out
 */
protected void printType(ReferenceType rt){
  System.out.println("ref.type: " + rt);
  System.out.println("name: " + rt.name());
  try {
    System.out.println("sourceName: " + rt.sourceName());
  }
 catch (  AbsentInformationException ex) {
    System.out.println("sourceName: unknown");
  }
  System.out.println("methods:");
  for (  Method m : rt.methods()) {
    System.out.println(m.toString());
  }
}
