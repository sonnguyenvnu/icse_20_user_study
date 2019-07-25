private void combine(PRAuxiliaryModel orig,ArrayList<Callable<Double>> tasks){
  for (int i=0; i < tasks.size(); i++) {
    ExpectationTask task=(ExpectationTask)tasks.get(i);
    PRAuxiliaryModel model=task.getModelCopy();
    for (int ci=0; ci < model.numConstraints(); ci++) {
      PRConstraint origConstraint=orig.getConstraint(ci);
      PRConstraint copyConstraint=model.getConstraint(ci);
      double[] expectation=new double[origConstraint.numDimensions()];
      copyConstraint.getExpectations(expectation);
      origConstraint.addExpectations(expectation);
    }
  }
}
