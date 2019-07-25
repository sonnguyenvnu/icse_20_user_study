/** 
 * Draws a 2-dimensional representation of a tree.
 * @param s A show instance for the elements of the tree.
 * @return a String showing this tree in two dimensions.
 */
public String draw(final Show<A> s){
  return Monoid.stringMonoid.join(drawTree(s),"\n");
}
