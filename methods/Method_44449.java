/** 
 * Retrieves all ledger entries between the start date and the end date. This method iterates over ledger pages until it has retrieved all entries between the start date and the end date. The ledger records the activity (trades, deposit, withdrawals) of the account for all assets.
 * @param assets Set of assets to restrict output to (can be null, defaults to all)
 * @param ledgerType {@link LedgerType} to retrieve (can be null, defaults to all types)
 * @param start Start unix timestamp or ledger id of results (can be null)
 * @param end End unix timestamp or ledger id of results (can be null)
 * @param offset Result offset (can be null)
 * @return
 * @throws IOException
 */
public Map<String,KrakenLedger> getKrakenLedgerInfo(LedgerType ledgerType,Date start,Date end,Long offset,Currency... assets) throws IOException {
  String startTime=null;
  String endTime=null;
  long longOffset=0;
  if (start != null) {
    startTime=String.valueOf(DateUtils.toUnixTime(start));
  }
  if (end != null) {
    endTime=String.valueOf(DateUtils.toUnixTime(end));
  }
  if (offset != null) {
    longOffset=offset;
  }
  Map<String,KrakenLedger> fullLedgerMap=getKrakenPartialLedgerInfo(ledgerType,startTime,endTime,offset,assets);
  Map<String,KrakenLedger> lastLedgerMap=fullLedgerMap;
  while (!lastLedgerMap.isEmpty()) {
    longOffset+=lastLedgerMap.size();
    lastLedgerMap=getKrakenPartialLedgerInfo(ledgerType,startTime,endTime,longOffset,assets);
    if (lastLedgerMap.size() == 1 && fullLedgerMap.keySet().containsAll(lastLedgerMap.keySet())) {
      break;
    }
    fullLedgerMap.putAll(lastLedgerMap);
  }
  return fullLedgerMap;
}
