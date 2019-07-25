public Instance pipe(Instance carrier){
  TokenSequence ts=(TokenSequence)carrier.getData();
  int tsSize=ts.size();
  for (int i=tsSize - 1; i >= 0; i--) {
    Token t=ts.get(i);
    String text=t.getText();
    if (featureRegex != null && !featureRegex.matcher(text).matches())     continue;
    for (int j=0; j < i; j++) {
      if (ts.get(j).getText().equals(text)) {
        PropertyList.Iterator iter=ts.get(j).getFeatures().iterator();
        while (iter.hasNext()) {
          iter.next();
          String key=iter.getKey();
          if (filterRegex == null || (filterRegex.matcher(key).matches() ^ !includeFiltered))           t.setFeatureValue(namePrefix + key,iter.getNumericValue());
        }
        break;
      }
      if (firstMentionName != null)       t.setFeatureValue(firstMentionName,1.0);
    }
  }
  return carrier;
}
