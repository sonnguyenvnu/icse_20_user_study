private void nexta(){
  try {
    if (this.a != null && this.a.hasNext())     this.na=this.a.next();
 else     this.na=null;
  }
 catch (  final ConcurrentModificationException e) {
    this.na=null;
  }
}
