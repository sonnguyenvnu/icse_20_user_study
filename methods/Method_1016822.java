@Override public Instance pipe(Instance carrier){
  TokenSequence ts=(TokenSequence)carrier.getData();
  for (int i=0; i < ts.size(); i++) {
    Token t=ts.get(i);
    String s=t.getText();
    if (s.indexOf("&") != -1) {
      if (s.indexOf("&amp;") != -1)       return carrier;
 else {
        t.setText(s.replaceAll("&","&amp;"));
      }
    }
  }
  return carrier;
}
