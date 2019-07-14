private Language findLanguageByName(String languageName){
  if (languageName != null) {
    if (languageName.equals(Languages.java().getName())) {
      return Languages.java();
    }
 else     if (languageName.equals(Languages.bytecode().getName())) {
      return Languages.bytecode();
    }
 else     if (languageName.equals(Languages.bytecodeAst().getName())) {
      return Languages.bytecodeAst();
    }
    for (    Language language : Languages.debug()) {
      if (languageName.equals(language.getName())) {
        return language;
      }
    }
  }
  return Languages.java();
}
