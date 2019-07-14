protected int countDimension(List<ParseTree> children){
  int dimension=0;
  for (  ParseTree child : children) {
    if (child instanceof TerminalNodeImpl) {
      if (((TerminalNodeImpl)child).getSymbol().getType() == JavaParser.LBRACK)       dimension++;
    }
  }
  return dimension;
}
