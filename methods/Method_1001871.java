public void fill(int windingRule){
  if (hidden == false) {
    final Element elt=(Element)document.createElement("path");
    elt.setAttribute("d",currentPath.toString());
    getG().appendChild(elt);
  }
  currentPath=null;
}
