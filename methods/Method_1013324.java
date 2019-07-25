public FPSet init(int numThreads,String metadir,String fname){
  this.metadir=metadir;
  this.filename=metadir + FileUtil.separator + fname;
  return this;
}
