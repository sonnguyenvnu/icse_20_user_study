/** 
 * This method adds (destructively) a new element in the front  of the list.
 */
public final void push(Object value){
  ConsCell cell=new ConsCell(value,this.first);
  this.first=cell;
  if (this.last == null)   this.last=cell;
}
