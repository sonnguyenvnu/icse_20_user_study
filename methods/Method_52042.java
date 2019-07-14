/** 
 * We only need to report if this is comparing against one of the comparison targets
 * @param equality
 * @return true if this is comparing to one of the comparison targets elsefalse
 * @see #getComparisonTargets()
 */
private boolean isCompare(Node equality){
  if (isLiteralLeftHand(equality)) {
    return checkComparison(inverse.get(equality.getImage()),equality,0);
  }
 else   if (isLiteralRightHand(equality)) {
    return checkComparison(equality.getImage(),equality,1);
  }
  return false;
}
