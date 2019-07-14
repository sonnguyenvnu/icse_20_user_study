public BitcointoyouOrderResponse sell(LimitOrder limitOrder) throws IOException {
  return createOrder("sell",limitOrder);
}
