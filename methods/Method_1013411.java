/** 
 * This method is the cdr equivalent. It returns a new list of removing the first front element.
 */
public final List cdr(){
  List newList=new List();
  newList.first=this.first.next;
  newList.last=(newList.first == null) ? null : this.last;
  return newList;
}
