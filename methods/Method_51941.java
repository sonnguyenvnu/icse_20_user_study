private void moveToNext(){
  while (i < parent.jjtGetNumChildren() && !(targetChildType.isInstance(parent.jjtGetChild(i)))) {
    i++;
  }
}
