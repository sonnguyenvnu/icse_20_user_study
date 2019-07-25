@Override PageBtree split(int splitPoint){
  int newPageId=index.getPageStore().allocatePage();
  PageBtreeNode p2=PageBtreeNode.create(index,newPageId,parentPageId);
  index.getPageStore().logUndo(this,data);
  if (onlyPosition) {
    p2.onlyPosition=true;
  }
  int firstChild=childPageIds[splitPoint];
  readAllRows();
  while (splitPoint < entryCount) {
    p2.addChild(p2.entryCount,childPageIds[splitPoint + 1],getRow(splitPoint));
    removeChild(splitPoint);
  }
  int lastChild=childPageIds[splitPoint - 1];
  removeChild(splitPoint - 1);
  childPageIds[splitPoint - 1]=lastChild;
  if (p2.childPageIds == null) {
    p2.childPageIds=new int[1];
  }
  p2.childPageIds[0]=firstChild;
  p2.remapChildren();
  return p2;
}
