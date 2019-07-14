/** 
 * Support file opening for  {@link DocumentFile} (eg. OTG)
 */
public static void openFile(final DocumentFile f,final MainActivity m,SharedPreferences sharedPrefs){
  boolean useNewStack=sharedPrefs.getBoolean(PreferencesConstants.PREFERENCE_TEXTEDITOR_NEWSTACK,false);
  try {
    openunknown(f,m,false,useNewStack);
  }
 catch (  Exception e) {
    Toast.makeText(m,m.getString(R.string.noappfound),Toast.LENGTH_LONG).show();
    openWith(f,m,useNewStack);
  }
}
