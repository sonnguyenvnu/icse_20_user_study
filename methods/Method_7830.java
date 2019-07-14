private boolean openAllParentBlocks(TL_pageBlockDetailsChild child){
  TLRPC.PageBlock parentBlock=getLastNonListPageBlock(child.parent);
  if (parentBlock instanceof TLRPC.TL_pageBlockDetails) {
    TLRPC.TL_pageBlockDetails blockDetails=(TLRPC.TL_pageBlockDetails)parentBlock;
    if (!blockDetails.open) {
      blockDetails.open=true;
      return true;
    }
    return false;
  }
 else   if (parentBlock instanceof TL_pageBlockDetailsChild) {
    TL_pageBlockDetailsChild parent=(TL_pageBlockDetailsChild)parentBlock;
    parentBlock=getLastNonListPageBlock(parent.block);
    boolean opened=false;
    if (parentBlock instanceof TLRPC.TL_pageBlockDetails) {
      TLRPC.TL_pageBlockDetails blockDetails=(TLRPC.TL_pageBlockDetails)parentBlock;
      if (!blockDetails.open) {
        blockDetails.open=true;
        opened=true;
      }
    }
    return openAllParentBlocks(parent) || opened;
  }
  return false;
}
