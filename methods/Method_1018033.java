@Override public Iterable<long[]> call(Iterator<CleansedRowResult> cleansedRowResultIterator) throws Exception {
  long[] validationCounts=new long[schemaLen + 2];
  while (cleansedRowResultIterator.hasNext()) {
    CleansedRowResult cleansedRowResult=cleansedRowResultIterator.next();
    for (int idx=0; idx < schemaLen; idx++) {
      if (!cleansedRowResult.isColumnValid(idx)) {
        validationCounts[idx]=validationCounts[idx] + 1L;
      }
    }
    if (cleansedRowResult.isRowValid()) {
      validationCounts[schemaLen]=validationCounts[schemaLen] + 1L;
    }
 else {
      validationCounts[schemaLen + 1]=validationCounts[schemaLen + 1] + 1L;
    }
  }
  List<long[]> results=new LinkedList<>();
  results.add(validationCounts);
  return results;
}
