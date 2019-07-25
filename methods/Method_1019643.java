/** 
 * Returns true if the given name match the name of this element and false otherwise.
 * @param name
 * @return
 */
public boolean match(String name){
  if (name == null) {
    return false;
  }
  return name.equals(this.name);
}
