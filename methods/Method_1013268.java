/** 
 * Starting / Finished used to print debugging information.            
 */
private static void Starting(String name){
  if (Parameters.Debug) {
    start=Debug.now();
    ToolIO.out.println("Starting " + name);
  }
}
