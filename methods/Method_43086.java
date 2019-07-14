public BitcointoyouOrderResponse buy(LimitOrder limitOrder) throws IOException {
  return createOrder("buy",limitOrder);
}
