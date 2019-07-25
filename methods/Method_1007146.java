/** 
 * Expands this tree into a tree of trees, with this tree as the root label, and subtrees as the labels of child nodes (comonad pattern).
 * @return A tree of trees, with this tree as its root label, and subtrees of this tree as the labels ofits child nodes.
 */
public Tree<Tree<A>> cojoin(){
  final F<Tree<A>,Tree<A>> id=identity();
  return cobind(id);
}
