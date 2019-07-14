@Override public String placeStopOrder(StopOrder stopOrder) throws IOException {
  return kucoinCreateOrder(KucoinAdapters.adaptStopOrder(stopOrder)).getOrderId();
}
