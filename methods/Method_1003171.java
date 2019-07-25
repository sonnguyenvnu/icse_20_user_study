@Override PageData split(int splitPoint){
  int newPageId=index.getPageStore().allocatePage();
  PageDataNode p2=PageDataNode.create(index,newPageId,parentPageId);
  int firstChild=childPageIds[splitPoint];
  while (splitPoint < entryCount) {
    p2.addChild(p2.entryCount,childPageIds[splitPoint + 1],keys[splitPoint]);
    removeChild(splitPoint);
  }
  int lastChild=childPageIds[splitPoint - 1];
  removeChild(splitPoint - 1);
  childPageIds[splitPoint - 1]=lastChild;
  p2.childPageIds[0]=firstChild;
  p2.remapChildren(getPos());
  return p2;
}
