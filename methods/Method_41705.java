/** 
 * {@inheritDoc}
 */
public synchronized void decrement(long numerator,long denominator){
  this.numeratorValue-=numerator;
  this.denominatorValue-=denominator;
}
