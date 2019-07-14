/** 
 * Retrieves a fraction of the ledger entries (usually a "page" of 50 entries) between the start date and the end date. The ledger records the activity (trades, deposit, withdrawals) of the account for all assets.
 * @param assets Set of assets to restrict output to (can be null, defaults to all)
 * @param ledgerType {@link LedgerType} to retrieve (can be null, defaults to all types)
 * @param startTime Start Unix timestamp or ledger id of results (can be null)
 * @param endTime End Unix timestamp or ledger id of results (can be null)
 * @param offset Result offset (can be null)
 * @return
 * @throws IOException
 */
public Map<String,KrakenLedger> getKrakenPartialLedgerInfo(LedgerType ledgerType,String startTime,String endTime,Long offset,Currency... assets) throws IOException {
  String ledgerTypeString=(ledgerType == null) ? "all" : ledgerType.toString().toLowerCase();
  KrakenLedgerResult ledgerResult=kraken.ledgers(null,delimitAssets(assets),ledgerTypeString,startTime,endTime,offset,exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory());
  return checkResult(ledgerResult).getLedgerMap();
}
