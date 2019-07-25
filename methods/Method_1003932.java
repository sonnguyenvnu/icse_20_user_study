/** 
 * Return the weight of the level ie. 2^(i-1) except for the two tree leaves (weight=1) and for the root
 */
private int weight(int level){
  if (level == 0) {
    return 1;
  }
 else   if (level == maxDepth) {
    return rootWeight;
  }
 else {
    return 1 << (level - 1);
  }
}
