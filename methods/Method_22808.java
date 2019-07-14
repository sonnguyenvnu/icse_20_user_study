private CharacterKinds CharacterKind(char ch,String noWordSep){
  if (Character.isLetterOrDigit(ch) || ch == '_' || noWordSep.indexOf(ch) != -1)   return CharacterKinds.Word;
 else   if (Character.isWhitespace(ch))   return CharacterKinds.Whitespace;
 else   return CharacterKinds.Other;
}
