public Instance pipe(Instance carrier){
  TokenSequence ts=(TokenSequence)carrier.getData();
  int tsSize=ts.size();
  PropertyList[] oldfs=new PropertyList[ts.size()];
  PropertyList[] newfs=new PropertyList[ts.size()];
  for (int i=0; i < tsSize; i++)   oldfs[i]=ts.get(i).getFeatures();
  if (includeOriginalSingletons)   for (int i=0; i < tsSize; i++)   newfs[i]=ts.get(i).getFeatures();
  for (int i=0; i < ts.size(); i++) {
    conjunctionList:     for (int j=0; j < conjunctions.length; j++) {
      for (int k=0; k < conjunctions[j].length; k++) {
        if (conjunctions[j][k] + i < 0 || conjunctions[j][k] + i > tsSize - 1 || oldfs[i + conjunctions[j][k]] == null)         continue conjunctionList;
      }
      if (conjunctions[j].length == 1) {
        int offset=conjunctions[j][0];
        if (offset == 0 && includeOriginalSingletons)         throw new IllegalArgumentException("Original singletons already there.");
        PropertyList.Iterator iter=oldfs[i + offset].iterator();
        while (iter.hasNext()) {
          iter.next();
          if (propertyKey != null && !propertyKey.equals(iter.getKey()))           continue;
          String key=iter.getKey() + (offset == 0 ? "" : "@" + offset);
          newfs[i]=PropertyList.add(key,iter.getNumericValue(),newfs[i]);
        }
      }
 else       if (conjunctions[j].length == 2) {
        int offset0=conjunctions[j][0];
        int offset1=conjunctions[j][1];
        PropertyList.Iterator iter0=oldfs[i + offset0].iterator();
        int iter0i=-1;
        while (iter0.hasNext()) {
          iter0i++;
          iter0.next();
          if (propertyKey != null && !propertyKey.equals(iter0.getKey()))           continue;
          PropertyList.Iterator iter1=oldfs[i + offset1].iterator();
          int iter1i=-1;
          while (iter1.hasNext()) {
            iter1i++;
            iter1.next();
            if (propertyKey != null && !propertyKey.equals(iter1.getKey()))             continue;
            if (offset0 == offset1 && iter1i <= iter0i)             continue;
            String key=iter0.getKey() + (offset0 == 0 ? "" : "@" + offset0) + "&" + iter1.getKey() + (offset1 == 0 ? "" : "@" + offset1);
            newfs[i]=PropertyList.add(key,iter0.getNumericValue() * iter1.getNumericValue(),newfs[i]);
          }
        }
      }
 else       if (conjunctions[j].length == 3) {
        int offset0=conjunctions[j][0];
        int offset1=conjunctions[j][1];
        int offset2=conjunctions[j][2];
        PropertyList.Iterator iter0=oldfs[i + offset0].iterator();
        int iter0i=-1;
        while (iter0.hasNext()) {
          iter0i++;
          iter0.next();
          if (propertyKey != null && !propertyKey.equals(iter0.getKey()))           continue;
          PropertyList.Iterator iter1=oldfs[i + offset1].iterator();
          int iter1i=-1;
          while (iter1.hasNext()) {
            iter1i++;
            iter1.next();
            if (propertyKey != null && !propertyKey.equals(iter1.getKey()))             continue;
            if (offset0 == offset1 && iter1i <= iter0i)             continue;
            PropertyList.Iterator iter2=oldfs[i + offset2].iterator();
            int iter2i=-1;
            while (iter2.hasNext()) {
              iter2i++;
              iter2.next();
              if (propertyKey != null && !propertyKey.equals(iter2.getKey()))               continue;
              if (offset1 == offset2 && iter2i <= iter1i)               continue;
              String key=iter0.getKey() + (offset0 == 0 ? "" : "@" + offset0) + "&" + iter1.getKey() + (offset1 == 0 ? "" : "@" + offset1) + "&" + iter2.getKey() + (offset2 == 0 ? "" : "@" + offset2);
              newfs[i]=PropertyList.add(key,iter0.getNumericValue() * iter1.getNumericValue() * iter2.getNumericValue(),newfs[i]);
            }
          }
        }
      }
 else {
        throw new UnsupportedOperationException("Conjunctions of length 4 or more not yet implemented.");
      }
    }
  }
  for (int i=0; i < ts.size(); i++)   ts.get(i).setFeatures(newfs[i]);
  return carrier;
}
