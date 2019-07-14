protected void retrieveDataFromBaseBinIndex(TimeBinIndex index){
  _min=index.getMin();
  _max=index.getMax();
  _step=index.getStep();
  _baseBins=index.getBins();
  _baseTimeCount=index.getTimeRowCount();
  _baseNonTimeCount=index.getNonTimeRowCount();
  _baseBlankCount=index.getBlankRowCount();
  _baseErrorCount=index.getErrorRowCount();
  if (_config.isSelected()) {
    _config._from=Math.max(_config._from,_min);
    _config._to=Math.min(_config._to,_max);
  }
 else {
    _config._from=_min;
    _config._to=_max;
  }
}
