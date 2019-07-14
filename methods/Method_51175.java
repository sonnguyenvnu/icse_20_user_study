public static LanguageVersion findLanguageVersionByTerseName(String terseNameAndVersion){
  String version;
  String terseName;
  if (terseNameAndVersion.contains(" ")) {
    version=StringUtils.trimToNull(terseNameAndVersion.substring(terseNameAndVersion.lastIndexOf(' ') + 1));
    terseName=terseNameAndVersion.substring(0,terseNameAndVersion.lastIndexOf(' '));
  }
 else {
    version=null;
    terseName=terseNameAndVersion;
  }
  Language language=findLanguageByTerseName(terseName);
  if (language != null) {
    if (version == null) {
      return language.getDefaultVersion();
    }
 else {
      return language.getVersion(version);
    }
  }
  return null;
}
