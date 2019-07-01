private synchronized void _XXXXX_(){
  if (null != ledgerIdLeftFromPrevAllocation) {
    LOG.info("Deleting allocated-but-unused ledger left from previous allocation {}.",ledgerIdLeftFromPrevAllocation);
    deleteLedger(ledgerIdLeftFromPrevAllocation);
    ledgerIdLeftFromPrevAllocation=null;
  }
}