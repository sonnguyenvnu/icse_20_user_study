/** 
 * @return Suggested strings based on last input.
 */
public List<String> suggest(){
  return suggestions.apply(root(),lastTarget);
}
