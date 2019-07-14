/** 
 * @param key ( the Key to used to retrieve this data later  )
 * @param value ( any kind of primitive values  ) <p/> non can be null!!!
 */
@SuppressLint("ApplySharedPref") public static <T>void set(@NonNull String key,@Nullable T value){
  if (InputHelper.isEmpty(key)) {
    throw new NullPointerException("Key must not be null! (key = " + key + "), (value = " + value + ")");
  }
  SharedPreferences.Editor edit=PreferenceManager.getDefaultSharedPreferences(App.getInstance()).edit();
  if (InputHelper.isEmpty(value)) {
    clearKey(key);
    return;
  }
  if (value instanceof String) {
    edit.putString(key,(String)value);
  }
 else   if (value instanceof Integer) {
    edit.putInt(key,(Integer)value);
  }
 else   if (value instanceof Long) {
    edit.putLong(key,(Long)value);
  }
 else   if (value instanceof Boolean) {
    edit.putBoolean(key,(Boolean)value);
  }
 else   if (value instanceof Float) {
    edit.putFloat(key,(Float)value);
  }
 else {
    edit.putString(key,value.toString());
  }
  edit.commit();
}
