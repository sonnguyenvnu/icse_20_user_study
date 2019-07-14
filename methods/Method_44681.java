/** 
 * ??OKEx??K???
 * @param currencyPair
 * @param type
 * @param contractType
 * @param size
 * @param since
 * @return
 * @throws IOException
 */
public List<OkCoinFutureKline> getFutureKline(CurrencyPair currencyPair,OkCoinKlineType type,FuturesContract contractType,Integer size,Long since) throws IOException {
  List<Object[]> list=okCoin.getFutureKline("1",OkCoinAdapters.adaptSymbol(currencyPair),type.getType(),contractType.getName(),size,since);
  List<OkCoinFutureKline> klineList=new ArrayList<>();
  list.stream().forEach(kline -> klineList.add(new OkCoinFutureKline(kline)));
  return klineList;
}
