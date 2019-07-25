public Instance pipe(Instance carrier){
  TokenSequence ts=(TokenSequence)carrier.getData();
  TokenSequence targets=carrier.getTarget() instanceof TokenSequence ? (TokenSequence)carrier.getTarget() : null;
  TokenSequence source=carrier.getSource() instanceof TokenSequence ? (TokenSequence)carrier.getSource() : null;
  StringBuffer sb=new StringBuffer();
  if (prefix != null)   sb.append(prefix);
  sb.append("name: " + carrier.getName() + "\n");
  for (int i=0; i < ts.size(); i++) {
    if (source != null) {
      sb.append(source.get(i).getText());
      sb.append(' ');
    }
    if (carrier.getTarget() instanceof TokenSequence) {
      sb.append(((TokenSequence)carrier.getTarget()).get(i).getText());
      sb.append(' ');
    }
    if (carrier.getTarget() instanceof FeatureSequence) {
      sb.append(((FeatureSequence)carrier.getTarget()).getObjectAtPosition(i).toString());
      sb.append(' ');
    }
    PropertyList pl=ts.get(i).getFeatures();
    if (pl != null) {
      PropertyList.Iterator iter=pl.iterator();
      while (iter.hasNext()) {
        iter.next();
        double v=iter.getNumericValue();
        if (v == 1.0)         sb.append(iter.getKey());
 else         sb.append(iter.getKey() + '=' + v);
        sb.append(' ');
      }
    }
    sb.append('\n');
  }
  System.out.print(sb.toString());
  return carrier;
}
