/** 
 * Gets the last token of the list.
 * @return the last token of the list
 */
public String tail(){
  return (list.size() < 1 || tail < 0 || tail >= list.size()) ? null : list.get(tail);
}
