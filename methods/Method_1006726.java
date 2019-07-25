/** 
 * Parse a TypeVariableSignature.
 * @param parser the parser
 * @param definingClassName the defining class name
 * @return the type variable signature
 * @throws ParseException if parsing fails
 */
static TypeVariableSignature parse(final Parser parser,final String definingClassName) throws ParseException {
  final char peek=parser.peek();
  if (peek == 'T') {
    parser.next();
    if (!TypeUtils.getIdentifierToken(parser)) {
      throw new ParseException(parser,"Could not parse type variable signature");
    }
    parser.expect(';');
    final TypeVariableSignature typeVariableSignature=new TypeVariableSignature(parser.currToken(),definingClassName);
    @SuppressWarnings("unchecked") List<TypeVariableSignature> typeVariableSignatures=(List<TypeVariableSignature>)parser.getState();
    if (typeVariableSignatures == null) {
      parser.setState(typeVariableSignatures=new ArrayList<>());
    }
    typeVariableSignatures.add(typeVariableSignature);
    return typeVariableSignature;
  }
 else {
    return null;
  }
}
