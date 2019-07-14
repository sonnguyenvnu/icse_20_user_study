public static List<Language> findByExtension(String extension){
  List<Language> languages=new ArrayList<>();
  for (  Language language : getInstance().languages.values()) {
    if (language.hasExtension(extension)) {
      languages.add(language);
    }
  }
  return languages;
}
