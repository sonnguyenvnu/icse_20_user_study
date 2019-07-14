protected void retrieveDataFromBinner(ExpressionNumericValueBinner binner){
  _bins=binner.bins;
  _numericCount=binner.numericCount;
  _nonNumericCount=binner.nonNumericCount;
  _blankCount=binner.blankCount;
  _errorCount=binner.errorCount;
}
