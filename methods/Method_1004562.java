/** 
 * Loading a map from preferences, using a prefix for the prefs keys
 * @since 5.6.5
 * @param pPrefs
 * @param pMap
 * @param pPrefix
 */
private static void load(final SharedPreferences pPrefs,final Map<String,String> pMap,final String pPrefix){
  if (pPrefix == null || pMap == null)   return;
  pMap.clear();
  for (  final String key : pPrefs.getAll().keySet()) {
    if (key != null && key.startsWith(pPrefix)) {
      pMap.put(key.substring(pPrefix.length()),pPrefs.getString(key,null));
    }
  }
}
