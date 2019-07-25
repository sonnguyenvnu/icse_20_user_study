public long transaction(byte[] sellTokenID,long sellTokenQuant){
  long supply=1_000_000_000_000_000_000L;
  ExchangeProcessor processor=new ExchangeProcessor(supply);
  long buyTokenQuant=0;
  long firstTokenBalance=this.exchange.getFirstTokenBalance();
  long secondTokenBalance=this.exchange.getSecondTokenBalance();
  if (this.exchange.getFirstTokenId().equals(ByteString.copyFrom(sellTokenID))) {
    buyTokenQuant=processor.exchange(firstTokenBalance,secondTokenBalance,sellTokenQuant);
    this.exchange=this.exchange.toBuilder().setFirstTokenBalance(firstTokenBalance + sellTokenQuant).setSecondTokenBalance(secondTokenBalance - buyTokenQuant).build();
  }
 else {
    buyTokenQuant=processor.exchange(secondTokenBalance,firstTokenBalance,sellTokenQuant);
    this.exchange=this.exchange.toBuilder().setFirstTokenBalance(firstTokenBalance - buyTokenQuant).setSecondTokenBalance(secondTokenBalance + sellTokenQuant).build();
  }
  return buyTokenQuant;
}
