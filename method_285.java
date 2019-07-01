private SingleDirectoryDbLedgerStorage _XXXXX_(long ledgerId){
  return ledgerStorageList.get(MathUtils.signSafeMod(ledgerId,numberOfDirs));
}