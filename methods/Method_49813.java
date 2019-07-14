public SMILLayoutElement getLayout(){
  Node headElement=getHead();
  Node layoutElement=null;
  layoutElement=headElement.getFirstChild();
  while ((layoutElement != null) && !(layoutElement instanceof SMILLayoutElement)) {
    layoutElement=layoutElement.getNextSibling();
  }
  if (layoutElement == null) {
    layoutElement=new SmilLayoutElementImpl(this,"layout");
    headElement.appendChild(layoutElement);
  }
  return (SMILLayoutElement)layoutElement;
}
