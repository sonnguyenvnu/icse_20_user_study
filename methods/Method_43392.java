public BitZKline getBitZKline(String pair,String type) throws IOException {
  return bitz.getKlineResult(pair,type).getData();
}
