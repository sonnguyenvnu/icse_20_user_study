/** 
 * Return the number of bits required to contain an integer based on the current length of the keep. As the keep fills up, the number of bits required to identify one of its items goes up.
 */
public int bitsize(){
  while (1 << this.power < this.length) {
    this.power+=1;
  }
  return this.power;
}
