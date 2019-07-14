public BitfinexDepositAddressResponse requestDepositAddressRaw(String currency) throws IOException {
  String type="unknown";
  if (currency.equalsIgnoreCase("BTC")) {
    type="bitcoin";
  }
 else   if (currency.equalsIgnoreCase("LTC")) {
    type="litecoin";
  }
 else   if (currency.equalsIgnoreCase("ETH")) {
    type="ethereum";
  }
 else   if (currency.equalsIgnoreCase("IOT")) {
    type="iota";
  }
 else   if (currency.equalsIgnoreCase("BCH")) {
    type="bcash";
  }
 else   if (currency.equalsIgnoreCase("BTG")) {
    type="bgold";
  }
  BitfinexDepositAddressResponse requestDepositAddressResponse=bitfinex.requestDeposit(apiKey,payloadCreator,signatureCreator,new BitfinexDepositAddressRequest(String.valueOf(exchange.getNonceFactory().createValue()),type,"exchange",0));
  if (requestDepositAddressResponse != null) {
    return requestDepositAddressResponse;
  }
 else {
    return null;
  }
}
