/** 
 * Collects all key/value pairs in SharedPreferences. The application default SharedPreferences are always collected, and the developer can provide additional SharedPreferences names in the  {@link org.acra.annotation.AcraCore#additionalSharedPreferences()}configuration item.
 * @return the collected key/value pairs.
 */
@NonNull private JSONObject collect(@NonNull Context context,@NonNull CoreConfiguration config) throws JSONException {
  final JSONObject result=new JSONObject();
  final Map<String,SharedPreferences> sharedPrefs=new TreeMap<>();
  sharedPrefs.put("default",PreferenceManager.getDefaultSharedPreferences(context));
  for (  final String sharedPrefId : config.additionalSharedPreferences()) {
    sharedPrefs.put(sharedPrefId,context.getSharedPreferences(sharedPrefId,Context.MODE_PRIVATE));
  }
  for (  Map.Entry<String,SharedPreferences> entry : sharedPrefs.entrySet()) {
    final String sharedPrefId=entry.getKey();
    final SharedPreferences prefs=entry.getValue();
    final Map<String,?> prefEntries=prefs.getAll();
    if (prefEntries.isEmpty()) {
      result.put(sharedPrefId,"empty");
    }
 else {
      for (final Iterator<String> iterator=prefEntries.keySet().iterator(); iterator.hasNext(); ) {
        if (filteredKey(config,iterator.next())) {
          iterator.remove();
        }
      }
      result.put(sharedPrefId,new JSONObject(prefEntries));
    }
  }
  return result;
}
