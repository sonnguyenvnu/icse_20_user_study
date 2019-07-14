/** 
 * ???????(et == null ? toast : hint)
 * @param context
 * @param et
 * @param string
 * @return
 */
public static boolean showInputedError(Activity context,EditText et,String string){
  if (context == null || StringUtil.isNotEmpty(string,false) == false) {
    Log.e(TAG,"showInputedError  context == null || et == null || StringUtil.isNotEmpty(string, false) == false >> return false;");
    return false;
  }
  if (et == null) {
    Toast.makeText(context,StringUtil.getTrimedString(string),Toast.LENGTH_SHORT).show();
  }
 else {
    et.setText("");
    et.setHint(string);
    et.setHintTextColor(context.getResources().getColor(R.color.red));
  }
  return false;
}
