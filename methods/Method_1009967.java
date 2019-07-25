/** 
 * @param x the value at which the CDF should be evaluated
 * @return the approximate fraction of all samples that were less than or equal to x.
 */
@Override public double cdf(double x){
  AVLGroupTree values=summary;
  if (values.size() == 0) {
    return Double.NaN;
  }
 else   if (values.size() == 1) {
    if (x < values.mean(values.first()))     return 0;
 else     if (x > values.mean(values.first()))     return 1;
 else     return 0.5;
  }
 else {
    if (x < min) {
      return 0;
    }
 else     if (x == min) {
      return 0.5 / size();
    }
    assert x > min;
    if (x > max) {
      return 1;
    }
 else     if (x == max) {
      long n=size();
      return (n - 0.5) / n;
    }
    assert x < max;
    int first=values.first();
    double firstMean=values.mean(first);
    if (x > min && x < firstMean) {
      return interpolateTail(values,x,first,firstMean,min);
    }
    int last=values.last();
    double lastMean=values.mean(last);
    if (x < max && x > lastMean) {
      return 1 - interpolateTail(values,x,last,lastMean,max);
    }
    assert values.size() >= 2;
    assert x >= firstMean;
    assert x <= lastMean;
    Iterator<Centroid> it=values.iterator();
    Centroid a=it.next();
    double aMean=a.mean();
    double aWeight=a.count();
    if (x == aMean) {
      return aWeight / 2.0 / size();
    }
    assert x > aMean;
    Centroid b=it.next();
    double bMean=b.mean();
    double bWeight=b.count();
    assert bMean >= aMean;
    double weightSoFar=0;
    while (bWeight > 0) {
      assert x > aMean;
      if (x == bMean) {
        assert bMean > aMean;
        weightSoFar+=aWeight;
        while (it.hasNext()) {
          b=it.next();
          if (x == b.mean()) {
            bWeight+=b.count();
          }
 else {
            break;
          }
        }
        return (weightSoFar + aWeight + bWeight / 2.0) / size();
      }
      assert x < bMean || x > bMean;
      if (x < bMean) {
        assert aMean < bMean;
        if (aWeight == 1) {
          if (bWeight == 1) {
            return (weightSoFar + 1.0) / size();
          }
 else {
            double partialWeight=(x - aMean) / (bMean - aMean) * bWeight / 2.0;
            return (weightSoFar + 1.0 + partialWeight) / size();
          }
        }
 else         if (bWeight == 1) {
          double partialWeight=(x - aMean) / (bMean - aMean) * aWeight / 2.0;
          return (weightSoFar + aWeight / 2.0 + partialWeight) / size();
        }
 else {
          double partialWeight=(x - aMean) / (bMean - aMean) * (aWeight + bWeight) / 2.0;
          return (weightSoFar + aWeight / 2.0 + partialWeight) / size();
        }
      }
      weightSoFar+=aWeight;
      assert x > bMean;
      if (it.hasNext()) {
        aMean=bMean;
        aWeight=bWeight;
        b=it.next();
        bMean=b.mean();
        bWeight=b.count();
        assert bMean >= aMean;
      }
 else {
        bWeight=0;
      }
    }
    throw new IllegalStateException("Ran out of centroids");
  }
}
