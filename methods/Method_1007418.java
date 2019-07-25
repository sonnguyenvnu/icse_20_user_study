/** 
 * Intersection.
 * @param r1 the r1
 * @param r2 the r2
 * @return the list
 */
private List<Restriction> intersection(List<Restriction> r1,List<Restriction> r2){
  List<Restriction> restrictions=new ArrayList<>(r1.size() + r2.size());
  Iterator<Restriction> i1=r1.iterator();
  Iterator<Restriction> i2=r2.iterator();
  Restriction res1=i1.next();
  Restriction res2=i2.next();
  boolean done=false;
  while (!done) {
    if (res1.getLowerBound() == null || res2.getUpperBound() == null || res1.getLowerBound().compareTo(res2.getUpperBound()) <= 0) {
      if (res1.getUpperBound() == null || res2.getLowerBound() == null || res1.getUpperBound().compareTo(res2.getLowerBound()) >= 0) {
        ArtifactVersion lower;
        ArtifactVersion upper;
        boolean lowerInclusive;
        boolean upperInclusive;
        if (res1.getLowerBound() == null) {
          lower=res2.getLowerBound();
          lowerInclusive=res2.isLowerBoundInclusive();
        }
 else         if (res2.getLowerBound() == null) {
          lower=res1.getLowerBound();
          lowerInclusive=res1.isLowerBoundInclusive();
        }
 else {
          int comparison=res1.getLowerBound().compareTo(res2.getLowerBound());
          if (comparison < 0) {
            lower=res2.getLowerBound();
            lowerInclusive=res2.isLowerBoundInclusive();
          }
 else           if (comparison == 0) {
            lower=res1.getLowerBound();
            lowerInclusive=res1.isLowerBoundInclusive() && res2.isLowerBoundInclusive();
          }
 else {
            lower=res1.getLowerBound();
            lowerInclusive=res1.isLowerBoundInclusive();
          }
        }
        if (res1.getUpperBound() == null) {
          upper=res2.getUpperBound();
          upperInclusive=res2.isUpperBoundInclusive();
        }
 else         if (res2.getUpperBound() == null) {
          upper=res1.getUpperBound();
          upperInclusive=res1.isUpperBoundInclusive();
        }
 else {
          int comparison=res1.getUpperBound().compareTo(res2.getUpperBound());
          if (comparison < 0) {
            upper=res1.getUpperBound();
            upperInclusive=res1.isUpperBoundInclusive();
          }
 else           if (comparison == 0) {
            upper=res1.getUpperBound();
            upperInclusive=res1.isUpperBoundInclusive() && res2.isUpperBoundInclusive();
          }
 else {
            upper=res2.getUpperBound();
            upperInclusive=res2.isUpperBoundInclusive();
          }
        }
        if (lower == null || upper == null || lower.compareTo(upper) != 0) {
          restrictions.add(new Restriction(lower,lowerInclusive,upper,upperInclusive));
        }
 else         if (lowerInclusive && upperInclusive) {
          restrictions.add(new Restriction(lower,lowerInclusive,upper,upperInclusive));
        }
        if (upper == res2.getUpperBound()) {
          if (i2.hasNext()) {
            res2=i2.next();
          }
 else {
            done=true;
          }
        }
 else {
          if (i1.hasNext()) {
            res1=i1.next();
          }
 else {
            done=true;
          }
        }
      }
 else {
        if (i1.hasNext()) {
          res1=i1.next();
        }
 else {
          done=true;
        }
      }
    }
 else {
      if (i2.hasNext()) {
        res2=i2.next();
      }
 else {
        done=true;
      }
    }
  }
  return restrictions;
}
