public void visit(WikiLinkNode node){
  if (isSet(NO_WIKI_LINKS)) {
    printer.printEncoded("[[" + node.getText() + "]]");
  }
 else {
    printLink(node,linkRenderer.render(node));
  }
}
