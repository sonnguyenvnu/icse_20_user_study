public Long totals(RawQuery query) throws BackendException {
  return index.totals(query,keyInformation,indexTx);
}
