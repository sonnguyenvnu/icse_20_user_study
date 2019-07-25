public Instance pipe(Instance carrier){
  TokenSequence ts=(TokenSequence)carrier.getData();
  int tsSize=ts.size();
  PropertyList[] newFeatures=new PropertyList[tsSize];
  for (int i=0; i < tsSize; i++) {
    Token t=ts.get(i);
    PropertyList pl=t.getFeatures();
    newFeatures[i]=pl;
    for (int position=i + leftBoundary; position < i + rightBoundary; position++) {
      if (position == i && !includeCurrentToken)       continue;
      PropertyList pl2;
      if (position < 0)       pl2=startfs[-position];
 else       if (position >= tsSize)       pl2=endfs[position - tsSize];
 else       pl2=ts.get(position).getFeatures();
      PropertyList.Iterator pl2i=pl2.iterator();
      while (pl2i.hasNext()) {
        pl2i.next();
        String key=pl2i.getKey();
        if (featureRegex == null || featureRegex.matcher(key).matches()) {
          newFeatures[i]=PropertyList.add((namePrefixLeft == null || position - i > 0 ? namePrefix : namePrefixLeft) + key,pl2i.getNumericValue(),newFeatures[i]);
        }
      }
    }
  }
  for (int i=0; i < tsSize; i++) {
    ts.get(i).setFeatures(newFeatures[i]);
  }
  return carrier;
}
