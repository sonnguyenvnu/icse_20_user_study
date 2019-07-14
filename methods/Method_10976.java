/** 
 * v4????? ?????Fragment ???????? FrameLayout ??
 * @param fragmentActivity
 * @param fragmentLazy
 * @param r_id_fragment    <FrameLayout android:id="@+id/r_id_fragment"/>
 */
public static void showFragmentLazy(FragmentActivity fragmentActivity,FragmentLazy fragmentLazy,int r_id_fragment){
  FragmentManager fragmentManager=fragmentActivity.getSupportFragmentManager();
  FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
  fragmentTransaction.replace(r_id_fragment,fragmentLazy);
  fragmentTransaction.commit();
  fragmentLazy.onHiddenChanged(true);
  fragmentLazy.onHiddenChanged(false);
}
