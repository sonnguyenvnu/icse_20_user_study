/** 
 * Calculates the number of possible method pairs of two methods.
 * @param methods Number of methods in the class
 * @return Number of possible method pairs
 */
private int maxMethodPairs(int methods){
  return methods * (methods - 1) / 2;
}
