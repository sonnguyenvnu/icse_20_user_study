/** 
 * Get dirs that stores ledger data.
 * @return ledger dirs
 */
public File[] _XXXXX_(){
  String[] ledgerDirNames=getLedgerDirNames();
  File[] ledgerDirs=new File[ledgerDirNames.length];
  for (int i=0; i < ledgerDirNames.length; i++) {
    ledgerDirs[i]=new File(ledgerDirNames[i]);
  }
  return ledgerDirs;
}