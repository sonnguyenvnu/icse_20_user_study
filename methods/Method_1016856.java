public Instance pipe(Instance carrier){
  TokenSequence ts=(TokenSequence)carrier.getData();
  if (ts.size() > 3 && (ts.get(2).getText().equals("-") || ts.get(3).getText().equals("-")) && ts.get(1).getText().matches("[A-Z]+")) {
    String header=ts.get(1).getText();
    if (header.equals("PRESS"))     return carrier;
    String featureName="HEADER=" + header;
    for (int i=0; i < ts.size(); i++) {
      Token t=ts.get(i);
      if (t.getText().matches("^[A-Z].*"))       t.setFeatureValue(featureName,1.0);
    }
  }
  return carrier;
}
