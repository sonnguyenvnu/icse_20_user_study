private LuytenPreferences loadLuytenPreferences(Preferences prefs) throws Exception {
  LuytenPreferences newLuytenPrefs=new LuytenPreferences();
  for (  Field field : LuytenPreferences.class.getDeclaredFields()) {
    if (Modifier.isStatic(field.getModifiers()))     continue;
    field.setAccessible(true);
    String prefId=field.getName();
    Object defaultVal=field.get(newLuytenPrefs);
    if (field.getType() == String.class) {
      String defaultStr=(String)(defaultVal == null ? "" : defaultVal);
      field.set(newLuytenPrefs,prefs.get(prefId,defaultStr));
    }
 else     if (field.getType() == Boolean.class || field.getType() == boolean.class) {
      Boolean defaultBool=(Boolean)(defaultVal == null ? new Boolean(false) : defaultVal);
      field.setBoolean(newLuytenPrefs,prefs.getBoolean(prefId,defaultBool));
    }
 else     if (field.getType() == Integer.class || field.getType() == int.class) {
      Integer defaultInt=(Integer)(defaultVal == null ? new Integer(0) : defaultVal);
      field.setInt(newLuytenPrefs,prefs.getInt(prefId,defaultInt));
    }
  }
  return newLuytenPrefs;
}
