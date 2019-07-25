@Override public boolean next() throws SQLServerException {
  batchParamIndex++;
  return batchParamIndex < batchParam.size();
}
