/** 
 * Prints the stack table map.
 * @param ps    a print stream such as <code>System.out</code>.
 */
public void println(java.io.PrintStream ps){
  Printer.print(this,new java.io.PrintWriter(ps,true));
}
