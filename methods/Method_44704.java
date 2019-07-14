private List<OkexTransaction> fetchTradesForOrder(OkexOpenOrder o) throws IOException {
  String from=null;
  List<OkexTransaction> all=new ArrayList<>();
  boolean stop=false;
  do {
    List<OkexTransaction> l=getTransactionDetails(o.getOrderId(),o.getInstrumentId(),null,null,null);
    all.addAll(l);
    stop=l.size() < transactions_limit;
    if (!stop) {
      from=l.get(l.size() - 1).getOrderId();
    }
  }
 while (!stop);
  return all;
}
