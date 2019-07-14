/** 
 * Adds elements to the set of matched elements.
 */
public Jerry add(final String selector){
  return new Jerry(this,nodes,root().find(selector).nodes);
}
