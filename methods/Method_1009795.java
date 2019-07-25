public void feed(RESFlvData flvData,int type){
synchronized (syncOp) {
    workHandler.sendFood(flvData,type);
  }
}
