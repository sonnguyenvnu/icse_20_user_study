/** 
 * ???????? 
 * @param context
 * @param phoneList
 */
public static void toMessageChat(Activity context,List<String> phoneList){
  if (context == null || phoneList == null || phoneList.size() <= 0) {
    Log.e(TAG,"sendMessage context == null || phoneList == null || phoneList.size() <= 0 " + ">> showShortToast(context, ???????~); return; ");
    showShortToast(context,"???????~");
    return;
  }
  String phones="";
  for (int i=0; i < phoneList.size(); i++) {
    phones+=phoneList.get(i) + ";";
  }
  toMessageChat(context,phones);
}
