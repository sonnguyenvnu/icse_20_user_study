@NotNull private static Resolvable resolvable(@NotNull Parent parent,@NotNull ASTNode[] children){
  Resolvable resolvable;
  if (children.length == 0) {
    resolvable=new Exact(":\"\"");
  }
 else {
    List<String> regexList=new LinkedList<>();
    List<Integer> codePointList=null;
    for (    ASTNode child : children) {
      IElementType elementType=child.getElementType();
      if (elementType == parent.getFragmentType()) {
        codePointList=parent.addFragmentCodePoints(codePointList,child);
      }
 else       if (elementType == ElixirTypes.ESCAPED_CHARACTER) {
        codePointList=parent.addEscapedCharacterCodePoints(codePointList,child);
      }
 else       if (elementType == ElixirTypes.ESCAPED_EOL) {
        codePointList=parent.addEscapedEOL(codePointList,child);
      }
 else       if (elementType == ElixirTypes.HEXADECIMAL_ESCAPE_PREFIX) {
        codePointList=addChildTextCodePoints(codePointList,child);
      }
 else       if (elementType == ElixirTypes.INTERPOLATION) {
        if (codePointList != null) {
          regexList.add(codePointListToString(codePointList));
          codePointList=null;
        }
        regexList.add(interpolation());
      }
 else       if (elementType == ElixirTypes.QUOTE_HEXADECIMAL_ESCAPE_SEQUENCE || elementType == ElixirTypes.SIGIL_HEXADECIMAL_ESCAPE_SEQUENCE) {
        codePointList=parent.addHexadecimalEscapeSequenceCodePoints(codePointList,child);
      }
 else {
        throw new NotImplementedException("Can't convert to Resolvable " + child);
      }
    }
    if (codePointList != null && regexList.isEmpty()) {
      resolvable=resolvableLiteral(codePointList);
    }
 else {
      if (codePointList != null) {
        regexList.add(codePointListToRegex(codePointList));
      }
      resolvable=new Pattern(join(regexList));
    }
  }
  return resolvable;
}
