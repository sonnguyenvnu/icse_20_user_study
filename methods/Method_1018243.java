/** 
 * Register a value in the keep. Compact the keep if it is full. The next time this value is encountered, its integer can be sent instead.
 * @param value A value.
 */
public void register(Object value){
  if (JSONzip.probe) {
    int integer=find(value);
    if (integer >= 0) {
      JSONzip.log("\nDuplicate key " + value);
    }
  }
  if (this.length >= this.capacity) {
    compact();
  }
  this.list[this.length]=value;
  this.map.put(value,this.length);
  this.ticks[this.length]=1;
  if (JSONzip.probe) {
    JSONzip.log("<" + this.length + " " + value + "> ");
  }
  this.length+=1;
}
