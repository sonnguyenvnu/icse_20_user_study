public static MigrationScriptReference deserialize(String s){
  int version=Integer.parseInt(s.substring(s.indexOf('/') + 1));
  int ix=s.indexOf('(');
  SLanguage language=MetaAdapterFactory.getLanguage(SLanguageId.deserialize(s.substring(0,ix)),s.substring(ix + 1,s.indexOf(')',ix)));
  return new MigrationScriptReference(language,version);
}
