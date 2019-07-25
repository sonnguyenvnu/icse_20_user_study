public Instance pipe(Instance carrier){
  if (!(carrier.getData() instanceof CharSequence))   throw new IllegalArgumentException();
  String s=carrier.getData().toString();
  String[] lines=s.split(System.getProperty("line.separator"));
  carrier.setData(new TokenSequence(lines));
  return carrier;
}
