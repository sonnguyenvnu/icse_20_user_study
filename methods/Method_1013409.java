/** 
 * This method is the cons equivalent. It returns a new list of adding a new element in the front.
 */
public final List cons(Object value){
  ConsCell cell=new ConsCell(value,this.first);
  List newList=new List();
  newList.first=cell;
  newList.last=(this.last == null) ? cell : this.last;
  return newList;
}
