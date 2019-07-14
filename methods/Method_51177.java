public static List<LanguageVersion> findAllVersions(){
  List<LanguageVersion> versions=new ArrayList<>();
  for (  Language language : getLanguages()) {
    versions.addAll(language.getVersions());
  }
  return versions;
}
