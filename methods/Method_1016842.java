@Override public Instance pipe(Instance carrier){
  if (carrier.getData() instanceof TokenSequence) {
    TokenSequence data=(TokenSequence)carrier.getData();
    carrier.setData(stem(data));
    return carrier;
  }
 else {
    throw new IllegalArgumentException("TokenSequence2PorterStems expects a TokenSequence, found a " + carrier.getData().getClass());
  }
}
