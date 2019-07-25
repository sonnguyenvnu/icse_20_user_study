public String serialize(){
  return MetaIdHelper.getLanguage(language).serialize() + "(" + language.getQualifiedName() + ")" + "/" + fromVersion;
}
