/** 
 * Sorts heap with heap sort; displays ordered elements to console.
 * @return sorted array of sorted elements
 */
public final int[] sort(){
  this.makeMinHeap(0);
  int[] sorted=new int[this.size];
  int index=0;
  while (this.size > 0) {
    int min=this.getRoot();
    sorted[index]=min;
    index++;
  }
  return sorted;
}
