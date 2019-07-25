private static void decode(final MessageHeaderDecoder messageHeader,final MarketDataIncrementalRefreshTradesDecoder marketData,final UnsafeBuffer buffer,final int bufferIndex){
  messageHeader.wrap(buffer,bufferIndex);
  final int actingVersion=messageHeader.version();
  final int actingBlockLength=messageHeader.blockLength();
  marketData.wrap(buffer,bufferIndex + messageHeader.encodedLength(),actingBlockLength,actingVersion);
  marketData.transactTime();
  marketData.eventTimeDelta();
  marketData.matchEventIndicator();
  for (  final MarketDataIncrementalRefreshTradesDecoder.MdIncGrpDecoder mdIncGrp : marketData.mdIncGrp()) {
    mdIncGrp.tradeId();
    mdIncGrp.securityId();
    mdIncGrp.mdEntryPx().mantissa();
    mdIncGrp.mdEntrySize().mantissa();
    mdIncGrp.numberOfOrders();
    mdIncGrp.mdUpdateAction();
    mdIncGrp.rptSeq();
    mdIncGrp.aggressorSide();
    mdIncGrp.mdEntryType();
  }
}
