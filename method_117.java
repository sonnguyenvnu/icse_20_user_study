@Override public void _XXXXX_(long ledgerID,long entryID,byte[] data,Consumer<Integer> cb){
  LedgerHandle handle;
  handle=openHandles.get(ledgerID);
  if (handle == null) {
    cb.accept(BKException.Code.WriteException);
    return;
  }
  handle.asyncAddEntry(entryID,data,(rc,lh,entryId,ctx) -> {
    cb.accept(rc);
  }
,null);
}