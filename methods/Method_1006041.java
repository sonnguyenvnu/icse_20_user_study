public static Map<String,List<String>> create(JabRefPreferences preferences){
  Map<String,List<String>> tabs=new LinkedHashMap<>();
  int i=0;
  String name;
  if (preferences.hasKey(JabRefPreferences.CUSTOM_TAB_NAME + 0)) {
    while (preferences.hasKey(JabRefPreferences.CUSTOM_TAB_NAME + i)) {
      name=preferences.get(JabRefPreferences.CUSTOM_TAB_NAME + i);
      List<String> entry=Arrays.asList(preferences.get(JabRefPreferences.CUSTOM_TAB_FIELDS + i).split(";"));
      tabs.put(name,entry);
      i++;
    }
  }
 else {
    while (preferences.get(JabRefPreferences.CUSTOM_TAB_NAME + "_def" + i) != null) {
      name=preferences.get(JabRefPreferences.CUSTOM_TAB_NAME + "_def" + i);
      List<String> entry=Arrays.asList(preferences.get(JabRefPreferences.CUSTOM_TAB_FIELDS + "_def" + i).split(";"));
      tabs.put(name,entry);
      i++;
    }
  }
  return tabs;
}
