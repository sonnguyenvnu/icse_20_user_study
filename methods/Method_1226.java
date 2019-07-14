/** 
 * Returns true if the argument is a top-level Drawable in this holder. 
 */
public boolean verifyDrawable(Drawable who){
  for (int i=0; i < mHolders.size(); ++i) {
    if (who == get(i).getTopLevelDrawable()) {
      return true;
    }
  }
  return false;
}
