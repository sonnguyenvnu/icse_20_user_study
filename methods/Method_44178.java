public static Exchange createTestExchange(){
  Exchange lakeBtcExchange=ExchangeFactory.INSTANCE.createExchange(LakeBTCExchange.class.getName());
  return lakeBtcExchange;
}
