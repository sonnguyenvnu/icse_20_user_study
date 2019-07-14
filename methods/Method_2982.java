/** 
 * Shows whether the tree to train is projective or not
 * @return true if the tree is non-projective
 */
public boolean isNonprojective(){
  for (  int dep1 : goldDependencies.keySet()) {
    int head1=goldDependencies.get(dep1).headIndex;
    for (    int dep2 : goldDependencies.keySet()) {
      int head2=goldDependencies.get(dep2).headIndex;
      if (head1 < 0 || head2 < 0)       continue;
      if (dep1 > head1 && head1 != head2)       if ((dep1 > head2 && dep1 < dep2 && head1 < head2) || (dep1 < head2 && dep1 > dep2 && head1 < dep2))       return true;
      if (dep1 < head1 && head1 != head2)       if ((head1 > head2 && head1 < dep2 && dep1 < head2) || (head1 < head2 && head1 > dep2 && dep1 < dep2))       return true;
    }
  }
  return false;
}
