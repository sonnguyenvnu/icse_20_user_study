@NotNull public static Resolvable resolvable(@NotNull ElixirAtom atom){
  ElixirCharListLine charListLine=atom.getCharListLine();
  Resolvable resolvable;
  if (charListLine != null) {
    resolvable=resolvable(charListLine);
  }
 else {
    ElixirStringLine stringLine=atom.getStringLine();
    if (stringLine != null) {
      resolvable=resolvable(stringLine);
    }
 else {
      ASTNode atomNode=atom.getNode();
      ASTNode atomFragmentNode=atomNode.getLastChildNode();
      assert atomFragmentNode.getElementType() == ElixirTypes.ATOM_FRAGMENT;
      resolvable=new Exact(":" + atomFragmentNode.getText());
    }
  }
  return resolvable;
}
