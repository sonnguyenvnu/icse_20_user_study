public static Map<String,HuobiBalanceSum> adaptBalance(HuobiBalanceRecord[] huobiBalance){
  Map<String,HuobiBalanceSum> map=new HashMap<>();
  for (  HuobiBalanceRecord record : huobiBalance) {
    HuobiBalanceSum sum=map.get(record.getCurrency());
    if (sum == null) {
      sum=new HuobiBalanceSum();
      map.put(record.getCurrency(),sum);
    }
    if (record.getType().equals("trade")) {
      sum.setAvailable(record.getBalance());
    }
 else     if (record.getType().equals("frozen")) {
      sum.setFrozen(record.getBalance());
    }
  }
  return map;
}
