/** 
 * ?????? 
 */
public void setFragments(int index){
  for (int i=0; i < mFragments.size(); i++) {
    FragmentTransaction ft=mFragmentManager.beginTransaction();
    Fragment fragment=mFragments.get(i);
    if (i == index) {
      ft.show(fragment);
    }
 else {
      ft.hide(fragment);
    }
    ft.commit();
  }
  mCurrentTab=index;
}
