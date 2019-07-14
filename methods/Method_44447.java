/** 
 * Retrieves the full account Ledger which represents all account asset activity.
 * @return
 * @throws IOException
 */
public Map<String,KrakenLedger> getKrakenLedgerInfo() throws IOException {
  return getKrakenLedgerInfo(null,null,null,null);
}
