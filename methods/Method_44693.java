/** 
 * ???????
 * @param pair
 * @param type
 * @param status //?? 0???7???? 1:??7????
 * @param currentPage
 * @param pageNumber
 * @param pageLength //????????????50
 * @return
 */
public OkCoinFutureExplosiveResult futureExplosive(CurrencyPair pair,FuturesContract type,String status,Integer currentPage,Integer pageNumber,Integer pageLength){
  return okCoin.futureExplosive(apikey,OkCoinAdapters.adaptSymbol(pair),type.getName(),status,signatureCreator(),currentPage,pageNumber,pageLength);
}
