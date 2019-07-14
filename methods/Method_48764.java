public static void verifyName(String name){
  Preconditions.checkArgument(name.indexOf(Token.SEPARATOR_CHAR) < 0,"Name can not contains reserved character %s: %s",Token.SEPARATOR_CHAR,name);
}
