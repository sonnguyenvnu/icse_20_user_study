@Override public Tokenizer create(){
  if (config == null) {
    return new ICUTokenizer();
  }
 else {
    return new ICUTokenizer(config);
  }
}
