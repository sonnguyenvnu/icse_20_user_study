@Override public Instance pipe(Instance carrier){
  carrier.setData(new TokenSequence((CharSequence[])carrier.getData()));
  return carrier;
}
