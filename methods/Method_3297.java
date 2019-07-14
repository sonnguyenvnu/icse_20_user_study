public void setPenalty(int i,int j,double penalty){
  if (penalty_.isEmpty()) {
    for (int s=0; s < node_.size(); s++) {
      List<Double> penaltys=Arrays.asList(new Double[ysize_]);
      penalty_.add(penaltys);
    }
  }
  penalty_.get(i).set(j,penalty);
}
