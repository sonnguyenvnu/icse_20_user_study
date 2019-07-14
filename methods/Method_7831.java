private TLRPC.PageBlock fixListBlock(TLRPC.PageBlock parentBlock,TLRPC.PageBlock childBlock){
  if (parentBlock instanceof TL_pageBlockListItem) {
    TL_pageBlockListItem blockListItem=(TL_pageBlockListItem)parentBlock;
    blockListItem.blockItem=childBlock;
    return parentBlock;
  }
 else   if (parentBlock instanceof TL_pageBlockOrderedListItem) {
    TL_pageBlockOrderedListItem blockListItem=(TL_pageBlockOrderedListItem)parentBlock;
    blockListItem.blockItem=childBlock;
    return parentBlock;
  }
  return childBlock;
}
