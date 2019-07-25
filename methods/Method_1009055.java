/** 
 * Returns the length of the longest common sequence.
 * @return the length of the longest common sequence.
 */
public int length(){
  if (this.length1 == 0 || this.length2 == 0) {
    this.length=0;
  }
  if (this.length < 0) {
    this.matrix.setup(this.length1 + 1,this.length2 + 1);
    for (int i=super.length1; i >= 0; i--) {
      for (int j=super.length2; j >= 0; j--) {
        if (i >= super.length1 || j >= super.length2) {
          this.matrix.set(i,j,0);
        }
 else {
          if (this.sequence1.getEvent(i).equals(this.sequence2.getEvent(j))) {
            this.matrix.incrementPathBy(i,j,maxWeight(this.sequence1.getEvent(i),this.sequence2.getEvent(j)));
          }
 else {
            this.matrix.incrementByMaxPath(i,j);
          }
        }
      }
    }
    this.length=this.matrix.get(0,0);
  }
  if (DEBUG) {
    System.err.println();
    for (int i=0; i < this.sequence1.size(); i++) {
      System.err.print(ShortStringFormatter.toShortString(this.sequence1.getEvent(i)) + "\t");
    }
    System.err.println();
    for (int i=0; i < this.sequence2.size(); i++) {
      System.err.print(ShortStringFormatter.toShortString(this.sequence2.getEvent(i)) + "\t");
    }
    System.err.println(this.matrix);
  }
  return this.length;
}
