static private boolean isIntegral(Number o){
  if (o instanceof Integer || o instanceof Long) {
    return true;
  }
 else {
    return (o.doubleValue() - o.longValue()) == 0;
  }
}
