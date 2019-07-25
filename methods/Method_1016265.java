/** 
 * {@inheritDoc}
 */
@Override public int compare(Component o1,Component o2){
  Container parent1=o1.getParent();
  Container parent2=o2.getParent();
  int z1=parent1.getComponentZOrder(o1);
  int z2=parent2.getComponentZOrder(o2);
  return z1 < z2 ? -1 : (z1 == z2 ? 0 : 1);
}
