public void evaluate(String description,Extraction extraction,PrintStream out){
  int numDocs=extraction.getNumDocuments();
  assert numDocs == extraction.getNumRecords();
  Vector entityConfidences=new Vector();
  int numTrueValues=0;
  int numPredValues=0;
  int numCorrValues=0;
  for (int docnum=0; docnum < numDocs; docnum++) {
    Record extracted=extraction.getRecord(docnum);
    Record target=extraction.getTargetRecord(docnum);
    Iterator it=extracted.fieldsIterator();
    while (it.hasNext()) {
      Field predField=(Field)it.next();
      Field trueField=target.getField(predField.getName());
      if (predField != null)       numPredValues+=predField.numValues();
      for (int j=0; j < predField.numValues(); j++) {
        LabeledSpan span=predField.span(j);
        boolean correct=(trueField != null && trueField.isValue(predField.value(j),comparator));
        entityConfidences.add(new ConfidenceEvaluator.EntityConfidence(span.getConfidence(),correct,span.getText()));
        if (correct)         numCorrValues++;
      }
    }
    it=target.fieldsIterator();
    while (it.hasNext()) {
      Field trueField=(Field)it.next();
      numTrueValues+=trueField.numValues();
    }
  }
  ConfidenceEvaluator evaluator=new ConfidenceEvaluator(entityConfidences,this.numberBins);
  out.println("correlation: " + evaluator.correlation());
  out.println("avg precision: " + evaluator.getAveragePrecision());
  out.println("coverage\taccuracy:\n" + evaluator.accuracyCoverageValuesToString());
  double[] ac=evaluator.getAccuracyCoverageValues();
  for (int i=0; i < ac.length; i++) {
    int marks=(int)(ac[i] * 25.0);
    for (int j=0; j < marks; j++)     out.print("*");
    out.println();
  }
  out.println("nTrue:" + numTrueValues + " nCorr:" + numCorrValues + " nPred:" + numPredValues + "\n");
  out.println("recall\taccuracy:\n" + evaluator.accuracyRecallValuesToString(numTrueValues));
}
