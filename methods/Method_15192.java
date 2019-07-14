/** 
 * ??edittext????????
 * @param context
 * @param et
 * @param type
 * @return
 */
public static boolean isInputedCorrect(Activity context,EditText et,int type,String errorRemind){
  if (context == null || et == null) {
    Log.e(TAG,"isInputedCorrect context == null || et == null >> return false;");
    return false;
  }
  oringinalHintColor=et.getHintTextColors();
  String inputed=StringUtil.getTrimedString(et);
switch (type) {
case TYPE_VERIFY:
    if (type == TYPE_VERIFY && inputed.length() < 4) {
      return showInputedError(context,et,StringUtil.isNotEmpty(errorRemind,true) ? errorRemind : "???????4?");
    }
  break;
case TYPE_PASSWORD:
if (inputed.length() < 6) {
  return showInputedError(context,et,StringUtil.isNotEmpty(errorRemind,true) ? errorRemind : "??????6?");
}
if (StringUtil.isNumberOrAlpha(inputed) == false) {
return showInputedError(context,et,StringUtil.isNotEmpty(errorRemind,true) ? errorRemind : "???????????");
}
break;
case TYPE_PHONE:
if (inputed.length() != 11) {
return showInputedError(context,et,StringUtil.isNotEmpty(errorRemind,true) ? errorRemind : "???11????");
}
if (StringUtil.isPhone(inputed) == false) {
Toast.makeText(context,"????????????~",Toast.LENGTH_SHORT).show();
return false;
}
break;
case TYPE_MAIL:
if (StringUtil.isEmail(inputed) == false) {
return showInputedError(context,"???????????~");
}
break;
default :
if (StringUtil.isNotEmpty(inputed,true) == false || inputed.equals(StringUtil.getTrimedString(et.getHint()))) {
return showInputedError(context,et,StringUtil.isNotEmpty(errorRemind,true) ? errorRemind : StringUtil.getTrimedString(et));
}
break;
}
et.setHintTextColor(oringinalHintColor);
return true;
}
