package org.knowm.xchange.okcoin.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.okcoin.FuturesContract;
import org.knowm.xchange.okcoin.OkCoin;
import org.knowm.xchange.okcoin.OkCoinAdapters;
import org.knowm.xchange.okcoin.dto.marketdata.OkCoinDepth;
import org.knowm.xchange.okcoin.dto.marketdata.OkCoinFutureComment;
import org.knowm.xchange.okcoin.dto.marketdata.OkCoinFutureHoldAmount;
import org.knowm.xchange.okcoin.dto.marketdata.OkCoinFutureKline;
import org.knowm.xchange.okcoin.dto.marketdata.OkCoinKline;
import org.knowm.xchange.okcoin.dto.marketdata.OkCoinKlineType;
import org.knowm.xchange.okcoin.dto.marketdata.OkCoinTickerResponse;
import org.knowm.xchange.okcoin.dto.marketdata.OkCoinTrade;
import si.mazi.rescu.RestProxyFactory;

public class OkCoinMarketDataServiceRaw extends OkCoinBaseService {

  private final OkCoin okCoin;

  /**
   * Constructor
   *
   * @param exchange
   */
  public OkCoinMarketDataServiceRaw(Exchange exchange) {

    super(exchange);

    okCoin =
        RestProxyFactory.createProxy(
            OkCoin.class, exchange.getExchangeSpecification().getSslUri(), getClientConfig());
  }

  /**
   * èŽ·å?–OKExå¸?å¸?è¡Œæƒ…
   *
   * @param currencyPair
   * @return
   * @throws IOException
   */
  public OkCoinTickerResponse getTicker(CurrencyPair currencyPair) throws IOException {
    return okCoin.getTicker("1", OkCoinAdapters.adaptSymbol(currencyPair));
  }

  /**
   * èŽ·å?–OKExå?ˆçº¦è¡Œæƒ…
   *
   * @param currencyPair
   * @param prompt
   * @return
   * @throws IOException
   */
  public OkCoinTickerResponse getFuturesTicker(CurrencyPair currencyPair, FuturesContract prompt)
      throws IOException {
    return okCoin.getFuturesTicker(OkCoinAdapters.adaptSymbol(currencyPair), prompt.getName());
  }

  /**
   * èŽ·å?–OKExå¸?å¸?å¸‚åœºæ·±åº¦
   *
   * @param currencyPair
   * @return
   * @throws IOException
   */
  public OkCoinDepth getDepth(CurrencyPair currencyPair) throws IOException {
    return getDepth(currencyPair, null);
  }

  /**
   * èŽ·å?–OKExå¸?å¸?å¸‚åœºæ·±åº¦
   *
   * @param currencyPair
   * @param size è®¾ç½®æŸ¥è¯¢æ•°æ?®æ?¡æ•°ï¼Œ[1,200]
   * @return
   * @throws IOException
   */
  public OkCoinDepth getDepth(CurrencyPair currencyPair, Integer size) throws IOException {
    size = (size == null || size < 1 || size > 200) ? 200 : size;
    return okCoin.getDepth("1", OkCoinAdapters.adaptSymbol(currencyPair), size);
  }

  /**
   * èŽ·å?–OKExå?ˆçº¦æ·±åº¦ä¿¡æ?¯
   *
   * @param currencyPair
   * @param prompt
   * @return
   * @throws IOException
   */
  public OkCoinDepth getFuturesDepth(CurrencyPair currencyPair, FuturesContract prompt)
      throws IOException {
    return okCoin.getFuturesDepth(
        "1", OkCoinAdapters.adaptSymbol(currencyPair), prompt.getName().toLowerCase());
  }

  /**
   * èŽ·å?–OKExå¸?å¸?äº¤æ˜“ä¿¡æ?¯(60æ?¡)
   *
   * @param currencyPair
   * @return
   * @throws IOException
   */
  public OkCoinTrade[] getTrades(CurrencyPair currencyPair) throws IOException {
    return getTrades(currencyPair, null);
  }

  /**
   * èŽ·å?–OKExå¸?å¸?äº¤æ˜“ä¿¡æ?¯(60æ?¡)
   *
   * @param currencyPair
   * @param since tid:äº¤æ˜“è®°å½•ID(è¿”å›žæ•°æ?®ä¸ºï¼šæœ€æ–°äº¤æ˜“ä¿¡æ?¯tidå€¼--å½“å‰?tidå€¼ä¹‹é—´çš„äº¤æ˜“ä¿¡æ?¯ ,ä½†æœ€å¤šè¿”å›ž60æ?¡æ•°æ?®)
   * @return
   * @throws IOException
   */
  public OkCoinTrade[] getTrades(CurrencyPair currencyPair, Long since) throws IOException {
    return okCoin.getTrades("1", OkCoinAdapters.adaptSymbol(currencyPair), since);
  }

  /**
   * èŽ·å?–OKExå?ˆçº¦äº¤æ˜“è®°å½•ä¿¡æ?¯
   *
   * @param currencyPair
   * @param prompt
   * @return
   * @throws IOException
   */
  public OkCoinTrade[] getFuturesTrades(CurrencyPair currencyPair, FuturesContract prompt)
      throws IOException {
    return okCoin.getFuturesTrades(
        "1", OkCoinAdapters.adaptSymbol(currencyPair), prompt.getName().toLowerCase());
  }

  /**
   * èŽ·å?–OKExå?ˆçº¦æŒ‡æ•°ä¿¡æ?¯
   *
   * @param currencyPair
   * @return
   * @throws IOException
   */
  public OkCoinFutureComment getFuturesIndex(CurrencyPair currencyPair) throws IOException {
    return okCoin.getFuturesIndex("1", OkCoinAdapters.adaptSymbol(currencyPair));
  }

  /**
   * @param symbol btc_usdt,ltc_usdt,eth_usdt ç­‰
   * @return
   * @throws IOException
   */
  public OkCoinFutureComment getFuturesIndex(String symbol) throws IOException {
    return okCoin.getFuturesIndex("1", symbol);
  }

  /**
   * èŽ·å?–ç¾Žå…ƒäººæ°‘å¸?æ±‡çŽ‡
   *
   * @return
   * @throws IOException
   */
  public OkCoinFutureComment getExchangRate_US_CH() throws IOException {
    return okCoin.getExchangRate_US_CH();
  }

  /**
   * èŽ·å?–äº¤å‰²é¢„ä¼°ä»·
   *
   * @param currencyPair
   * @return
   * @throws IOException
   */
  public OkCoinFutureComment getFutureEstimatedPrice(CurrencyPair currencyPair) throws IOException {
    return okCoin.getFutureEstimatedPrice("1", OkCoinAdapters.adaptSymbol(currencyPair));
  }

  /**
   * èŽ·å?–OKExå?ˆçº¦Kçº¿ä¿¡æ?¯
   *
   * @param currencyPair
   * @param type
   * @param contractType
   * @param size
   * @param since
   * @return
   * @throws IOException
   */
  public List<OkCoinFutureKline> getFutureKline(
      CurrencyPair currencyPair,
      OkCoinKlineType type,
      FuturesContract contractType,
      Integer size,
      Long since)
      throws IOException {
    List<Object[]> list =
        okCoin.getFutureKline(
            "1",
            OkCoinAdapters.adaptSymbol(currencyPair),
            type.getType(),
            contractType.getName(),
            size,
            since);
    List<OkCoinFutureKline> klineList = new ArrayList<>();
    list.stream().forEach(kline -> klineList.add(new OkCoinFutureKline(kline)));
    return klineList;
  }

  public List<OkCoinFutureKline> getFutureKline(
      CurrencyPair currencyPair, OkCoinKlineType type, FuturesContract contractType)
      throws IOException {
    return getFutureKline(currencyPair, type, contractType, 0, 0L);
  }

  public List<OkCoinFutureKline> getFutureKline(
      CurrencyPair currencyPair, OkCoinKlineType type, FuturesContract contractType, Integer size)
      throws IOException {
    return getFutureKline(currencyPair, type, contractType, size, 0L);
  }

  public List<OkCoinFutureKline> getFutureKline(
      CurrencyPair currencyPair, OkCoinKlineType type, FuturesContract contractType, Long since)
      throws IOException {
    return getFutureKline(currencyPair, type, contractType, 0, since);
  }

  /**
   * èŽ·å?–å½“å‰?å?¯ç”¨å?ˆçº¦æ€»æŒ?ä»“é‡?
   *
   * @param currencyPair
   * @param contractType
   * @return
   * @throws IOException
   */
  public OkCoinFutureHoldAmount[] getFutureHoldAmount(
      CurrencyPair currencyPair, FuturesContract contractType) throws IOException {
    return okCoin.getFutureHoldAmount(
        "1", OkCoinAdapters.adaptSymbol(currencyPair), contractType.getName());
  }

  /**
   * èŽ·å?–å?ˆçº¦æœ€é«˜é™?ä»·å’Œæœ€ä½Žé™?ä»·
   *
   * @param currencyPair
   * @param contractType
   * @return
   * @throws IOException
   */
  public OkCoinFutureComment getFuturePriceLimit(
      CurrencyPair currencyPair, FuturesContract contractType) throws IOException {
    return okCoin.getFuturePriceLimit(
        "1", OkCoinAdapters.adaptSymbol(currencyPair), contractType.getName());
  }

  /**
   * èŽ·å?–OKExå¸?å¸?Kçº¿æ•°æ?®(æ¯?ä¸ªå‘¨æœŸæ•°æ?®æ?¡æ•°2000å·¦å?³)
   *
   * @param currencyPair
   * @param type
   * @return
   * @throws IOException
   */
  public List<OkCoinKline> getKlines(CurrencyPair currencyPair, OkCoinKlineType type)
      throws IOException {
    return getKlines(currencyPair, type, null, null);
  }

  /**
   * èŽ·å?–OKExå¸?å¸?Kçº¿æ•°æ?®
   *
   * @param currencyPair
   * @param type
   * @param size æŒ‡å®šèŽ·å?–æ•°æ?®çš„æ?¡æ•°
   * @return
   * @throws IOException
   */
  public List<OkCoinKline> getKlines(CurrencyPair currencyPair, OkCoinKlineType type, Integer size)
      throws IOException {
    return getKlines(currencyPair, type, size, null);
  }

  /**
   * èŽ·å?–OKExå¸?å¸?Kçº¿æ•°æ?®
   *
   * @param currencyPair
   * @param type
   * @param timestamp æŒ‡å®šèŽ·å?–æ•°æ?®çš„æ?¡æ•°
   * @return
   * @throws IOException
   */
  public List<OkCoinKline> getKlines(
      CurrencyPair currencyPair, OkCoinKlineType type, Long timestamp) throws IOException {
    return getKlines(currencyPair, type, null, timestamp);
  }

  /**
   * èŽ·å?–OKExå¸?å¸?Kçº¿æ•°æ?®
   *
   * @param currencyPair
   * @param type
   * @param size
   * @param timestamp è¿”å›žè¯¥æ—¶é—´æˆ³ä»¥å?Žçš„æ•°æ?®
   * @throws IOException
   */
  public List<OkCoinKline> getKlines(
      CurrencyPair currencyPair, OkCoinKlineType type, Integer size, Long timestamp)
      throws IOException {
    List<OkCoinKline> klineList = new ArrayList<>();
    List<Object[]> list =
        okCoin.getKlines(OkCoinAdapters.adaptSymbol(currencyPair), type.getType(), size, timestamp);
    list.stream().forEach(kline -> klineList.add(new OkCoinKline(kline)));
    return klineList;
  }
}
