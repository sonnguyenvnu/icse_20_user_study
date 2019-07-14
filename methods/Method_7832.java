private TLRPC.PageBlock wrapInTableBlock(TLRPC.PageBlock parentBlock,TLRPC.PageBlock childBlock){
  if (parentBlock instanceof TL_pageBlockListItem) {
    TL_pageBlockListItem parent=(TL_pageBlockListItem)parentBlock;
    TL_pageBlockListItem item=new TL_pageBlockListItem();
    item.parent=parent.parent;
    item.blockItem=wrapInTableBlock(parent.blockItem,childBlock);
    return item;
  }
 else   if (parentBlock instanceof TL_pageBlockOrderedListItem) {
    TL_pageBlockOrderedListItem parent=(TL_pageBlockOrderedListItem)parentBlock;
    TL_pageBlockOrderedListItem item=new TL_pageBlockOrderedListItem();
    item.parent=parent.parent;
    item.blockItem=wrapInTableBlock(parent.blockItem,childBlock);
    return item;
  }
  return childBlock;
}
