public static void encode(final MessageHeaderEncoder messageHeader,final MarketDataIncrementalRefreshTradesEncoder marketData,final UnsafeBuffer buffer,final int bufferIndex){
  marketData.wrapAndApplyHeader(buffer,bufferIndex,messageHeader).transactTime(1234L).eventTimeDelta(987).matchEventIndicator(MatchEventIndicator.END_EVENT);
  final MarketDataIncrementalRefreshTradesEncoder.MdIncGrpEncoder mdIncGrp=marketData.mdIncGrpCount(2);
  mdIncGrp.next();
  mdIncGrp.tradeId(1234L);
  mdIncGrp.securityId(56789L);
  mdIncGrp.mdEntryPx().mantissa(50);
  mdIncGrp.mdEntrySize().mantissa(10);
  mdIncGrp.numberOfOrders(1);
  mdIncGrp.mdUpdateAction(MDUpdateAction.NEW);
  mdIncGrp.rptSeq((short)1);
  mdIncGrp.aggressorSide(Side.BUY);
  mdIncGrp.next();
  mdIncGrp.tradeId(1234L);
  mdIncGrp.securityId(56789L);
  mdIncGrp.mdEntryPx().mantissa(50);
  mdIncGrp.mdEntrySize().mantissa(10);
  mdIncGrp.numberOfOrders(1);
  mdIncGrp.mdUpdateAction(MDUpdateAction.NEW);
  mdIncGrp.rptSeq((short)1);
  mdIncGrp.aggressorSide(Side.SELL);
}
