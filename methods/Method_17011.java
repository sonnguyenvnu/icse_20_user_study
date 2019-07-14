@Override protected void profile(){
  if (reads) {
    reads();
  }
 else {
    writes();
  }
}
