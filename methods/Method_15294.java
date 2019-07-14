/** 
 * ????boolean
 * @param values
 */
public static void putAllBoolean(boolean[] values){
  if (values == null || values.length != KEYS.length) {
    Log.e(TAG,"putAllBoolean  values == null || values.length != KEYS.length >> return;");
    return;
  }
  Editor editor=context.getSharedPreferences(APP_SETTING,Context.MODE_PRIVATE).edit();
  editor.clear();
  for (int i=0; i < values.length; i++) {
    editor.putBoolean(KEYS[i],values[i]);
  }
  editor.commit();
  init(context);
}
