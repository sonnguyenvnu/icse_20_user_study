/** 
 * {@inheritDoc}
 */
public synchronized void increment(long numerator,long denominator){
  this.numeratorValue+=numerator;
  this.denominatorValue+=denominator;
}
