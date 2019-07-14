private boolean isListItemBlock(TLRPC.PageBlock block){
  return block instanceof TL_pageBlockListItem || block instanceof TL_pageBlockOrderedListItem;
}
