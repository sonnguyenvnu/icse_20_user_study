private Symbol getLexicallyEnclosing(ClassSymbol symbol,String name){
  Symbol current=symbol.owner;
  while (true) {
    if (current == null || current.getSimpleName().contentEquals(name)) {
      return current;
    }
    if (current != current.owner && current.owner instanceof Symbol.ClassSymbol) {
      current=current.owner;
    }
 else {
      return null;
    }
  }
}
