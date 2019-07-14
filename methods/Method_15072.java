/** 
 * ?????fragment
 * @param position
 */
public void selectFragment(int position){
  if (fragments == null || fragments.length != getCount()) {
    removeAll();
    fragments=new Fragment[getCount()];
  }
  if (currentPosition == position) {
    if (fragments[position] != null && fragments[position].isVisible()) {
      Log.e(TAG,"selectFragment currentPosition == position" + " >> fragments[position] != null && fragments[position].isVisible()" + " >> return;	");
      return;
    }
  }
  if (fragments[position] == null) {
    fragments[position]=getFragment(position);
  }
  FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
  fragmentTransaction.hide(fragments[currentPosition]);
  if (fragments[position].isAdded() == false) {
    fragmentTransaction.add(getFragmentContainerResId(),fragments[position]);
  }
  fragmentTransaction.show(fragments[position]).commit();
  setTabSelection(position);
  selectTab(position);
  this.currentPosition=position;
}
