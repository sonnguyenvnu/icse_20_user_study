/** 
 * android.app.Activity???? ?????Fragment ???????? FrameLayout ??
 * @param activity
 * @param fragment
 * @param r_id_fragment <FrameLayout android:id="@+id/r_id_fragment"/>
 */
public static void showFragment(Activity activity,android.app.Fragment fragment,int r_id_fragment){
  android.app.FragmentManager fragmentManager=activity.getFragmentManager();
  android.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
  fragmentTransaction.replace(r_id_fragment,fragment);
  fragmentTransaction.commit();
}
