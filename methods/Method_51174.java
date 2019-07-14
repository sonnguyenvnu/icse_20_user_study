public static Language findLanguageByTerseName(String terseName){
  for (  Language language : getInstance().languages.values()) {
    if (language.getTerseName().equals(terseName)) {
      return language;
    }
  }
  return null;
}
