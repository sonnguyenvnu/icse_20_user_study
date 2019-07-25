public void update(ProtectedTermsPreferences preferences){
  mainList.clear();
  for (  String filename : preferences.getEnabledInternalTermLists()) {
    if (INTERNAL_LISTS.containsKey(filename)) {
      mainList.add(readProtectedTermsListFromResource(filename,INTERNAL_LISTS.get(filename).get(),true));
    }
 else {
      LOGGER.warn("Protected terms resource '" + filename + "' is no longer available.");
    }
  }
  for (  String filename : preferences.getDisabledInternalTermLists()) {
    if (INTERNAL_LISTS.containsKey(filename)) {
      if (!preferences.getEnabledInternalTermLists().contains(filename)) {
        mainList.add(readProtectedTermsListFromResource(filename,INTERNAL_LISTS.get(filename).get(),false));
      }
    }
 else {
      LOGGER.warn("Protected terms resource '" + filename + "' is no longer available.");
    }
  }
  for (  String filename : INTERNAL_LISTS.keySet()) {
    if (!preferences.getEnabledInternalTermLists().contains(filename) && !preferences.getDisabledInternalTermLists().contains(filename)) {
      mainList.add(readProtectedTermsListFromResource(filename,INTERNAL_LISTS.get(filename).get(),true));
      LOGGER.warn("New protected terms resource '" + filename + "' is available and enabled by default.");
    }
  }
  for (  String filename : preferences.getEnabledExternalTermLists()) {
    try {
      mainList.add(readProtectedTermsListFromFile(new File(filename),true));
    }
 catch (    FileNotFoundException e) {
      LOGGER.warn("Cannot find protected terms file " + filename,e);
    }
  }
  for (  String filename : preferences.getDisabledExternalTermLists()) {
    if (!preferences.getEnabledExternalTermLists().contains(filename)) {
      try {
        mainList.add(readProtectedTermsListFromFile(new File(filename),false));
      }
 catch (      FileNotFoundException e) {
        LOGGER.warn("Cannot find protected terms file " + filename,e);
      }
    }
  }
}
