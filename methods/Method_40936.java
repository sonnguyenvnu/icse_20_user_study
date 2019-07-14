/** 
 * Returns the ratio value of positive bits to the number of occupied bits.
 */
public double negativeRatioValue(){
  return occupiedBits == 0 ? 0 : (double)negatives / (double)occupiedBits;
}
