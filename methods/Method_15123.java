/** 
 * ?????fragment
 * @param position
 */
public void selectFragment(int position){
  if (currentPosition == position) {
    if (needReload == false && fragments[position] != null && fragments[position].isVisible()) {
      Log.w(TAG,"selectFragment currentPosition == position" + " >> fragments[position] != null && fragments[position].isVisible()" + " >> return;	");
      return;
    }
  }
  if (needReload || fragments[position] == null) {
    fragments[position]=getFragment(position);
  }
  FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
  fragmentTransaction.hide(fragments[currentPosition]);
  if (fragments[position].isAdded() == false) {
    fragmentTransaction.add(R.id.flBaseTabFragmentContainer,fragments[position]);
  }
  fragmentTransaction.show(fragments[position]).commit();
  this.currentPosition=position;
}
