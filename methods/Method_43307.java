public static Map.Entry<String,BigDecimal> findNonzeroAmount(BitstampUserTransaction transaction) throws ExchangeException {
  for (  Map.Entry<String,BigDecimal> entry : transaction.getAmounts().entrySet()) {
    if (entry.getValue().abs().compareTo(new BigDecimal(1e-6)) == 1) {
      return entry;
    }
  }
  throw new ExchangeException("Could not find non-zero amount in transaction (id: " + transaction.getId() + ")");
}
