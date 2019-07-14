public Compilation parseApex(final String sourceCode) throws ParseException {
  TopLevelVisitor visitor=new TopLevelVisitor();
  Locations.useIndexFactory();
  CompilerService.INSTANCE.visitAstFromString(sourceCode,visitor);
  return visitor.getTopLevel();
}
