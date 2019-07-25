/** 
 * Returns a zipper over the tree of all possible permutations of this tree zipper (comonad pattern). This tree zipper becomes the focused node of the new zipper.
 * @return A tree zipper over the tree of all possible permutations of this tree zipper.
 */
public TreeZipper<TreeZipper<A>> positions(){
  final Tree<TreeZipper<A>> t=unfoldTree(TreeZipper.<A>dwn()).f(this);
  final Stream<Tree<TreeZipper<A>>> l=uf(TreeZipper.left_());
  final Stream<Tree<TreeZipper<A>>> r=uf(TreeZipper.right_());
  final Stream<P3<Stream<Tree<TreeZipper<A>>>,TreeZipper<A>,Stream<Tree<TreeZipper<A>>>>> p=unfold(o -> {
    Option<P2<P3<Stream<Tree<TreeZipper<A>>>,TreeZipper<A>,Stream<Tree<TreeZipper<A>>>>,Option<TreeZipper<A>>>> r1=none();
    for (    final TreeZipper<A> z : o) {
      r1=some(P.p(P.p(z.uf(TreeZipper.left_()),z,z.uf(TreeZipper.right_())),z.parent()));
    }
    return r1;
  }
,parent());
  return treeZipper(t,l,r,p);
}
