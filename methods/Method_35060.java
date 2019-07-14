private ParserException createParserException(){
  return new ParserException("One of the provided parsers has a wrong typing. " + "Make sure that parsers are passed in a correct order and the fromTypes match each other.");
}
