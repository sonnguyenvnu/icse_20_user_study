package org.knowm.xchange.okcoin.service;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.okcoin.FuturesContract;
import org.knowm.xchange.okcoin.OkCoinAdapters;
import org.knowm.xchange.okcoin.dto.trade.OkCoinFuturesOrderResult;
import org.knowm.xchange.okcoin.dto.trade.OkCoinFuturesTradeHistoryResult;
import org.knowm.xchange.okcoin.dto.trade.OkCoinOrderResult;
import org.knowm.xchange.okcoin.dto.trade.OkCoinPositionResult;
import org.knowm.xchange.okcoin.dto.trade.OkCoinPriceLimit;
import org.knowm.xchange.okcoin.dto.trade.OkCoinTradeResult;
import org.knowm.xchange.okcoin.dto.trade.result.OkCoinBatchTradeResult;
import org.knowm.xchange.okcoin.dto.trade.result.OkCoinFutureExplosiveResult;
import org.knowm.xchange.okcoin.dto.trade.result.OkCoinMoreTradeResult;

public class OkCoinTradeServiceRaw extends OKCoinBaseTradeService {

  protected static final String BATCH_DELIMITER = ",";

  /**
   * Constructor
   *
   * @param exchange
   */
  protected OkCoinTradeServiceRaw(Exchange exchange) {

    super(exchange);
  }

  /**
   * ä¸‹å?•äº¤æ˜“
   *
   * @param symbol
   * @param type
   * @param price
   * @param amount (å?ªèƒ½æ˜¯æ•´æ•°)
   * @return
   * @throws IOException
   */
  public OkCoinTradeResult trade(String symbol, String type, String price, String amount)
      throws IOException {
    OkCoinTradeResult tradeResult =
        okCoin.trade(apikey, symbol, type, price, amount, signatureCreator());
    return returnOrThrow(tradeResult);
  }

  public OkCoinTradeResult placeMarketOrderBuy(String symbol, String type, String price)
      throws IOException {
    OkCoinTradeResult tradeResult =
        okCoin.placeMarketOrderBuy(apikey, symbol, type, price, signatureCreator());
    return returnOrThrow(tradeResult);
  }

  public OkCoinTradeResult placeMarketOrderSell(String symbol, String type, String amount)
      throws IOException {
    OkCoinTradeResult tradeResult =
        okCoin.placeMarketOrderSell(apikey, symbol, type, amount, signatureCreator());
    return returnOrThrow(tradeResult);
  }

  /**
   * æ‰¹é‡?ä¸‹å?•
   *
   * @param symbol
   * @param type é™?ä»·å?•(buy/sell)
   * @param ordersData "[{price:3,amount:5,type:'sell'},{price:3,amount:3,type:'buy'}]"
   *     æœ€ç»ˆä¹°å?–ç±»åž‹ç”±orders_data ä¸­type ä¸ºå‡†ï¼Œå¦‚orders_dataä¸?è®¾å®štype åˆ™ç”±ä¸Šé?¢typeè®¾ç½®ä¸ºå‡†ã€‚ è‹¥ï¼Œä¸Šé?¢typeæ²¡æœ‰è®¾ç½®ï¼ŒorderData
   *     å¿…é¡»è®¾ç½®type
   * @return
   * @throws IOException
   */
  public OkCoinMoreTradeResult batchTrade(String symbol, String type, String ordersData)
      throws IOException {
    OkCoinMoreTradeResult tradeResult =
        okCoin.batchTrade(apikey, symbol, type, ordersData, signatureCreator());
    return returnOrThrow(tradeResult);
  }

  /**
   * å?•ç¬”è®¢å?•å?–æ¶ˆ
   *
   * @param orderId
   * @param symbol
   * @return
   * @throws IOException
   */
  public OkCoinTradeResult cancelOrder(long orderId, String symbol) throws IOException {

    OkCoinTradeResult tradeResult = okCoin.cancelOrder(apikey, orderId, symbol, signatureCreator());
    return returnOrThrow(tradeResult);
  }

  /**
   * å¤šç¬”è®¢å?•å?–æ¶ˆ ä¸€æ¬¡æœ€å¤šå?–æ¶ˆä¸‰ä¸ªè®¢å?•
   *
   * @param orderIds
   * @param symbol
   * @return
   * @throws IOException
   */
  public OkCoinBatchTradeResult cancelUpToThreeOrders(Set<Long> orderIds, String symbol)
      throws IOException {
    String ids =
        orderIds.stream().map(Object::toString).collect(Collectors.joining(BATCH_DELIMITER));
    return okCoin.cancelOrders(apikey, ids, symbol, signatureCreator());
  }

  /**
   * èŽ·å?–ç”¨æˆ·çš„æœªå®Œæˆ?çš„è®¢å?•ä¿¡æ?¯
   *
   * @param symbol
   * @return
   * @throws IOException
   */
  public OkCoinOrderResult getOrder(String symbol) throws IOException {
    return getOrder(-1, symbol);
  }

  /**
   * èŽ·å?–ç”¨æˆ·çš„è®¢å?•ä¿¡æ?¯
   *
   * @param orderId
   * @param symbol
   * @return
   * @throws IOException
   */
  public OkCoinOrderResult getOrder(long orderId, String symbol) throws IOException {

    OkCoinOrderResult orderResult = okCoin.getOrder(apikey, orderId, symbol, signatureCreator());
    return returnOrThrow(orderResult);
  }

  /**
   * æ‰¹é‡?èŽ·å?–ç”¨æˆ·è®¢å?•
   *
   * @param symbol
   * @param type æŸ¥è¯¢ç±»åž‹ 0:æœªå®Œæˆ?çš„è®¢å?• 1:å·²ç»?å®Œæˆ?çš„è®¢å?•
   * @param orderIds
   * @return
   */
  public OkCoinOrderResult getOrder(String symbol, Integer type, String orderIds)
      throws IOException {

    OkCoinOrderResult orderResult =
        okCoin.getOrders(apikey, type, orderIds, symbol, signatureCreator());
    return returnOrThrow(orderResult);
  }

  /**
   * èŽ·å?–åŽ†å?²è®¢å?•ä¿¡æ?¯ï¼Œå?ªè¿”å›žæœ€è¿‘ä¸¤å¤©çš„ä¿¡æ?¯
   *
   * @param symbol
   * @param status
   * @param currentPage
   * @param pageLength
   * @return
   * @throws IOException
   */
  public OkCoinOrderResult getOrderHistory(
      String symbol, String status, String currentPage, String pageLength) throws IOException {

    OkCoinOrderResult orderResult =
        okCoin.getOrderHistory(apikey, symbol, status, currentPage, pageLength, signatureCreator());
    return returnOrThrow(orderResult);
  }

  /** OkCoin.com Futures API */
  /**
   * å?ˆçº¦ä¸‹å?•
   *
   * @param symbol
   * @param type
   * @param price
   * @param amount
   * @param contract
   * @param matchPrice
   * @param leverRate
   * @return
   * @throws IOException
   */
  public OkCoinTradeResult futuresTrade(
      String symbol,
      String type,
      String price,
      String amount,
      FuturesContract contract,
      int matchPrice,
      int leverRate)
      throws IOException {

    OkCoinTradeResult tradeResult =
        okCoin.futuresTrade(
            apikey,
            symbol,
            contract.getName(),
            type,
            price,
            amount,
            matchPrice,
            leverRate,
            signatureCreator());
    return returnOrThrow(tradeResult);
  }

  /**
   * å?–æ¶ˆå?ˆçº¦è®¢å?•(å?•ä¸ªå?–æ¶ˆï¼Œå¤šä¸ªå?–æ¶ˆæ²¡æœ‰å®žçŽ°å¤„ç?†)
   *
   * @param orderId
   * @param symbol
   * @param contract
   * @return
   * @throws IOException
   */
  public OkCoinTradeResult futuresCancelOrder(long orderId, String symbol, FuturesContract contract)
      throws IOException {

    OkCoinTradeResult tradeResult =
        okCoin.futuresCancelOrder(
            apikey, String.valueOf(orderId), symbol, contract.getName(), signatureCreator());
    return returnOrThrow(tradeResult);
  }

  /**
   * èŽ·å?–å?ˆçº¦è®¢å?•ä¿¡æ?¯
   *
   * @param orderId
   * @param symbol
   * @param currentPage
   * @param pageLength
   * @param contract
   * @return
   * @throws IOException
   */
  public OkCoinFuturesOrderResult getFuturesOrder(
      long orderId, String symbol, String currentPage, String pageLength, FuturesContract contract)
      throws IOException {

    OkCoinFuturesOrderResult futuresOrder =
        okCoin.getFuturesOrder(
            apikey,
            orderId,
            symbol,
            "1",
            currentPage,
            pageLength,
            contract.getName(),
            signatureCreator());
    return returnOrThrow(futuresOrder);
  }

  public OkCoinPriceLimit getFuturesPriceLimits(CurrencyPair currencyPair, FuturesContract prompt)
      throws IOException {

    return okCoin.getFuturesPriceLimits(OkCoinAdapters.adaptSymbol(currencyPair), prompt.getName());
  }

  public OkCoinFuturesOrderResult getFuturesFilledOrder(
      long orderId, String symbol, String currentPage, String pageLength, FuturesContract contract)
      throws IOException {

    OkCoinFuturesOrderResult futuresOrder =
        okCoin.getFuturesOrder(
            apikey,
            orderId,
            symbol,
            "2",
            currentPage,
            pageLength,
            contract.getName(),
            signatureCreator());
    return returnOrThrow(futuresOrder);
  }

  /**
   * æ‰¹é‡?èŽ·å?–å?ˆçº¦è®¢å?•ä¿¡æ?¯
   *
   * @param orderIds
   * @param symbol
   * @param contract
   * @return
   * @throws IOException
   */
  public OkCoinFuturesOrderResult getFuturesOrders(
      String orderIds, String symbol, FuturesContract contract) throws IOException {

    OkCoinFuturesOrderResult futuresOrder =
        okCoin.getFuturesOrders(apikey, orderIds, symbol, contract.getName(), signatureCreator());
    return returnOrThrow(futuresOrder);
  }

  /**
   * èŽ·å?–OKEXå?ˆçº¦äº¤æ˜“åŽ†å?²ï¼ˆé?žä¸ªäººï¼‰
   *
   * @param symbol
   * @param since
   * @param date
   * @return
   * @throws IOException
   */
  public OkCoinFuturesTradeHistoryResult[] getFuturesTradesHistory(
      String symbol, long since, String date) throws IOException {

    OkCoinFuturesTradeHistoryResult[] futuresHistory =
        okCoin.getFuturesTradeHistory(apikey, since, symbol, date, signatureCreator());
    return (futuresHistory);
  }

  /**
   * èŽ·å?–ç”¨æˆ·æŒ?ä»“èŽ·å?–OKEXå?ˆçº¦è´¦æˆ·ä¿¡æ?¯ ï¼ˆå…¨ä»“ï¼‰
   *
   * @param symbol
   * @param contract
   * @return
   * @throws IOException
   */
  public OkCoinPositionResult getFuturesPosition(String symbol, FuturesContract contract)
      throws IOException {
    OkCoinPositionResult futuresPositionsCross =
        okCoin.getFuturesPositionsCross(apikey, symbol, contract.getName(), signatureCreator());

    return returnOrThrow(futuresPositionsCross);
  }

  public OkCoinPositionResult getFuturesPosition(String symbol, String contract)
      throws IOException {
    OkCoinPositionResult futuresPositionsCross =
        okCoin.getFuturesPositionsCross(apikey, symbol, contract, signatureCreator());

    return returnOrThrow(futuresPositionsCross);
  }

  /**
   * @param symbol
   * @param contractType
   * @param ordersData
   * @param leverRate
   * @throws IOException
   */
  public OkCoinMoreTradeResult futureBatchTrade(
      String symbol, String contractType, String ordersData, String leverRate) throws IOException {
    return okCoin.futureBatchTrade(
        apikey, symbol, contractType, ordersData, signatureCreator(), leverRate);
  }

  /**
   * é€?ä»“ç”¨æˆ·æŒ?ä»“æŸ¥è¯¢
   *
   * @param currencyPair
   * @param contract
   * @return
   * @throws IOException
   */
  public OkCoinPositionResult getFuturesPositionsFixed(
      CurrencyPair currencyPair, FuturesContract contract) throws IOException {
    return okCoin.getFuturesPositionsFixed(
        apikey, OkCoinAdapters.adaptSymbol(currencyPair), contract.getName(), signatureCreator());
  }

  /**
   * èŽ·å?–å?ˆçº¦çˆ†ä»“å?•
   *
   * @param pair
   * @param type
   * @param status //çŠ¶æ€? 0ï¼šæœ€è¿‘7å¤©æœªæˆ?äº¤ 1:æœ€è¿‘7å¤©å·²æˆ?äº¤
   * @param currentPage
   * @param pageNumber
   * @param pageLength //æ¯?é¡µèŽ·å?–æ?¡æ•°ï¼Œæœ€å¤šä¸?è¶…è¿‡50
   * @return
   */
  public OkCoinFutureExplosiveResult futureExplosive(
      CurrencyPair pair,
      FuturesContract type,
      String status,
      Integer currentPage,
      Integer pageNumber,
      Integer pageLength) {
    return okCoin.futureExplosive(
        apikey,
        OkCoinAdapters.adaptSymbol(pair),
        type.getName(),
        status,
        signatureCreator(),
        currentPage,
        pageNumber,
        pageLength);
  }
}
