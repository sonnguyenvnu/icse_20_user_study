private void nextb(){
  try {
    if (this.b != null && this.b.hasNext())     this.nb=this.b.next();
 else     this.nb=null;
  }
 catch (  final ConcurrentModificationException e) {
    this.nb=null;
  }
}
