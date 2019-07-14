public void setClazz(Class<? extends BaseRowModel> clazz){
  this.clazz=clazz;
  if (headLineMun == 0) {
    this.headLineMun=1;
  }
}
