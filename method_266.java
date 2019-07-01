@Override public void _XXXXX_(long ledgerID,long firstEntryID,long lastEntryID,BiConsumer<Integer,ArrayList<byte[]>> cb){
  client.asyncOpenLedgerNoRecovery(ledgerID,BookKeeper.DigestType.CRC32,new byte[0],(rc,lh,ctx) -> {
    if (rc != 0) {
      cb.accept(rc,null);
      return;
    }
    System.out.format("Got handle for read %d -> %d on ledger %d%n",firstEntryID,lastEntryID,ledgerID);
    lh.asyncReadEntries(firstEntryID,lastEntryID,(rc1,lh1,seq,ctx1) -> {
      System.out.format("Read cb %d -> %d on ledger %d%n",firstEntryID,lastEntryID,ledgerID);
      ArrayList<byte[]> results=new ArrayList<>();
      if (rc1 == 0) {
        while (seq.hasMoreElements()) {
          results.add(seq.nextElement().getEntry());
        }
        System.out.format("About to close handle for read %d -> %d on ledger %d%n",firstEntryID,lastEntryID,ledgerID);
      }
      lh.asyncClose((rc2,lh2,ctx2) -> {
        System.out.format("Closed handle for read %d -> %d on ledger %d result %d%n",firstEntryID,lastEntryID,ledgerID,rc2);
        cb.accept(rc1 == 0 ? rc2 : rc1,results);
      }
,null);
    }
,null);
  }
,null);
}