@Override protected TokenEntry processToken(Tokens tokenEntries,GenericToken currentToken,String fileName){
  String image=currentToken.getImage();
  Token javaToken=(Token)currentToken;
  constructorDetector.restoreConstructorToken(tokenEntries,javaToken);
  if (ignoreLiterals && (javaToken.kind == JavaParserConstants.STRING_LITERAL || javaToken.kind == JavaParserConstants.CHARACTER_LITERAL || javaToken.kind == JavaParserConstants.DECIMAL_LITERAL || javaToken.kind == JavaParserConstants.FLOATING_POINT_LITERAL)) {
    image=String.valueOf(javaToken.kind);
  }
  if (ignoreIdentifiers && javaToken.kind == JavaParserConstants.IDENTIFIER) {
    image=String.valueOf(javaToken.kind);
  }
  constructorDetector.processToken(javaToken);
  return new TokenEntry(image,fileName,currentToken.getBeginLine());
}
