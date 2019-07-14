/** 
 * Writes object's property name: string and a colon.
 */
public void writeName(final String name){
  if (name != null) {
    writeString(name);
  }
 else {
    write(NULL);
  }
  write(':');
}
