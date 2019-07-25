public Instance pipe(Instance carrier){
  TokenSequence originalSequence=(TokenSequence)carrier.getData();
  TokenSequence newSequence=new TokenSequence();
  for (int i=0; i < originalSequence.size(); i++) {
    Token t=originalSequence.get(i);
    boolean passed=true;
    String text=t.getText();
    for (    Pattern pattern : stopPatterns) {
      Matcher matcher=pattern.matcher(text);
      if (matcher.matches()) {
        passed=false;
        break;
      }
    }
    if (passed) {
      newSequence.add(t);
    }
  }
  carrier.setData(newSequence);
  return carrier;
}
