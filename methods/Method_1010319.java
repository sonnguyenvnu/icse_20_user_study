public static SLanguageAdapter deserialize(String s){
  if (s.startsWith(SLanguageAdapterById.LANGUAGE_PREFIX)) {
    return SLanguageAdapterById.deserialize(s);
  }
 else   if (s.startsWith(InvalidLanguage.INVALID_PREFIX)) {
    return InvalidLanguage.deserialize(s);
  }
 else {
    throw new FormatException("Illegal language type: " + s);
  }
}
