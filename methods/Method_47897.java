@Contract("null -> fail") private void check(Long value){
  if (value == null)   throw new RuntimeException("null check failed");
}
