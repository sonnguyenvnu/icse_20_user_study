/** 
 * Increase the weight associated with a value by 1.
 * @param value The number of the symbol to tick
 */
public void tick(int value){
  if (this.toLearn > 0) {
    this.toLearn-=1;
    this.symbols[value].weight+=1;
    this.upToDate=false;
  }
}
