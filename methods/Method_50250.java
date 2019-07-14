public static RxJob getDefault(){
  if (rxJob == null) {
    rxJob=new RxJob();
  }
  return rxJob;
}
