private void addHillClimber(){
  addField(double.class,"stepSize");
  addField(long.class,"adjustment");
  addField(int.class,"hitsInSample");
  addField(int.class,"missesInSample");
  addField(double.class,"previousSampleHitRate");
}
