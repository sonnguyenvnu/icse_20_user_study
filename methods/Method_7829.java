private TLRPC.PageBlock getLastNonListPageBlock(TLRPC.PageBlock block){
  if (block instanceof TL_pageBlockListItem) {
    TL_pageBlockListItem blockListItem=(TL_pageBlockListItem)block;
    if (blockListItem.blockItem != null) {
      return getLastNonListPageBlock(blockListItem.blockItem);
    }
 else {
      return blockListItem.blockItem;
    }
  }
 else   if (block instanceof TL_pageBlockOrderedListItem) {
    TL_pageBlockOrderedListItem blockListItem=(TL_pageBlockOrderedListItem)block;
    if (blockListItem.blockItem != null) {
      return getLastNonListPageBlock(blockListItem.blockItem);
    }
 else {
      return blockListItem.blockItem;
    }
  }
  return block;
}
