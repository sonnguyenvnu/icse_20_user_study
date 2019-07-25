@Override public void load(Map<String,String> options){
  setOutageDetection(options.get(OUTAGEDETECTION));
  setOutageDetectionInterval(options.get(OUTAGEDETECTIONINTERVAL));
}
