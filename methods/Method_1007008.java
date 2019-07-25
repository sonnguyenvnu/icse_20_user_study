@Override public Trade read() throws Exception {
  if (counter < limit) {
    counter++;
    return new Trade("isin" + counter,counter,new BigDecimal(counter),"customer" + counter);
  }
  return null;
}
