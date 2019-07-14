protected void addVersion(String version,LanguageVersionHandler languageVersionHandler,boolean isDefault){
  if (versions == null) {
    versions=new HashMap<>();
  }
  LanguageVersion languageVersion=new LanguageVersion(this,version,languageVersionHandler);
  versions.put(version,languageVersion);
  if (isDefault) {
    defaultVersion=languageVersion;
  }
}
