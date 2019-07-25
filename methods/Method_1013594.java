/** 
 * Equivalent to <code>this.open(new FileInputStream(name))</code>. 
 */
public void open(String name) throws IOException {
  this.open(new FileInputStream(name));
}
