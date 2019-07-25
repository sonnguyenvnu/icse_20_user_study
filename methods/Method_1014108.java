/** 
 * Gets the first token of the list.
 * @return the first token of the list
 */
public String head(){
  return (list.size() < 1 || head < 0 || head >= list.size()) ? null : list.get(head);
}
