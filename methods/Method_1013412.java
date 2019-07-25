public final List append(List lst){
  ConsCell cell=this.first;
  if (cell == null)   return new List(lst);
  List newList=new List(cell.value);
  cell=cell.next;
  while (cell != null) {
    ConsCell newCell=new ConsCell(cell.value,null);
    newList.last.next=newCell;
    newList.last=newCell;
    cell=cell.next;
  }
  cell=lst.first;
  while (cell != null) {
    ConsCell newCell=new ConsCell(cell.value,null);
    newList.last.next=newCell;
    newList.last=newCell;
    cell=cell.next;
  }
  return newList;
}
