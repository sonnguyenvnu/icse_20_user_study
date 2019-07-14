/** 
 * ???Toast??? :????
 * @param context Context
 * @param str     ???????
 * @param isLong  Toast.LENGTH_LONG / Toast.LENGTH_SHORT
 */
public static void showToast(Context context,String str,boolean isLong){
  if (isLong) {
    Toast.makeText(context,str,Toast.LENGTH_LONG).show();
  }
 else {
    Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
  }
}
