@Override public void init(boolean compress,int level){
  this.compress=compress;
  free();
  if (compress) {
    deflater=new Deflater(level);
  }
 else {
    inflater=new Inflater();
  }
}
