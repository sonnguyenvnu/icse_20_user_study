/** 
 * Saving a map into preferences, using a prefix for the prefs keys
 * @since 5.6.5
 * @param pPrefs
 * @param pEdit
 * @param pMap
 * @param pPrefix
 */
private static void save(final SharedPreferences pPrefs,final SharedPreferences.Editor pEdit,final Map<String,String> pMap,final String pPrefix){
  for (  final String key : pPrefs.getAll().keySet()) {
    if (key.startsWith(pPrefix)) {
      pEdit.remove(key);
    }
  }
  for (  final Map.Entry<String,String> entry : pMap.entrySet()) {
    final String key=pPrefix + entry.getKey();
    pEdit.putString(key,entry.getValue());
  }
}
