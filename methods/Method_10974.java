/** 
 * v4????? ?????Fragment ???????? FrameLayout ??
 * @param fragmentActivity
 * @param fragment
 * @param r_id_fragment    <FrameLayout android:id="@+id/r_id_fragment"/>
 */
public static void showFragment(FragmentActivity fragmentActivity,Fragment fragment,int r_id_fragment){
  FragmentManager fragmentManager=fragmentActivity.getSupportFragmentManager();
  FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
  fragmentTransaction.replace(r_id_fragment,fragment);
  fragmentTransaction.commit();
}
