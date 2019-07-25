@Override public CleansedRowResult call(@Nonnull final Row row) throws Exception {
  Map<Class,Class> validatorParamType=new HashMap<>();
  int nulls=hasProcessingDttm ? 1 : 0;
  Object[] newValues=new Object[dataTypes.length + 1];
  boolean rowValid=true;
  String sbRejectReason;
  List<ValidationResult> results=null;
  boolean[] columnsValid=new boolean[dataTypes.length];
  Map<Integer,Object> originalValues=new HashMap<>();
  for (int idx=0; idx < dataTypes.length; idx++) {
    ValidationResult result;
    FieldPolicy fieldPolicy=policies[idx];
    HCatDataType dataType=dataTypes[idx];
    boolean columnValid=true;
    boolean isBinaryType=dataType.getConvertibleType().equals(byte[].class);
    Object val=(idx == row.length() || row.isNullAt(idx) ? null : row.get(idx));
    if (dataType.isUnchecked()) {
      if (val == null) {
        nulls++;
      }
      newValues[idx]=val;
      originalValues.put(idx,val);
    }
 else {
      Object fieldValue=(val);
      boolean isEmpty;
      if (fieldValue == null) {
        nulls++;
      }
      originalValues.put(idx,fieldValue);
      StandardizationAndValidationResult standardizationAndValidationResult=standardizeAndValidateField(fieldPolicy,fieldValue,dataType,validatorParamType);
      result=standardizationAndValidationResult.getFinalValidationResult();
      fieldValue=result.isValid() ? standardizationAndValidationResult.getFieldValue() : fieldValue;
      isEmpty=((fieldValue == null) || (StringUtils.isEmpty(fieldValue.toString())));
      if (result.isValid() && isBinaryType && !(fieldValue instanceof byte[]) && !(fieldValue instanceof String)) {
        fieldValue=null;
      }
 else       if ((dataType.isNumeric() || isBinaryType) && isEmpty) {
        fieldValue=null;
      }
      newValues[idx]=fieldValue;
      if (!result.isValid()) {
        rowValid=false;
        results=(results == null ? new Vector<ValidationResult>() : results);
        results.addAll(standardizationAndValidationResult.getValidationResults());
        columnValid=false;
      }
    }
    columnsValid[idx]=columnValid;
  }
  if (nulls >= dataTypes.length) {
    rowValid=false;
    results=(results == null ? new Vector<ValidationResult>() : results);
    results.add(ValidationResult.failRow("empty","Row is empty"));
  }
  if (!rowValid) {
    for (int idx=0; idx < dataTypes.length; idx++) {
      if (newValues[idx] == null || originalValues.get(idx) == null || newValues[idx].getClass() != originalValues.get(idx).getClass()) {
        newValues[idx]=originalValues.get(idx);
      }
    }
  }
  sbRejectReason=toJSONArray(results);
  if (hasProcessingDttm) {
    newValues[dataTypes.length]=newValues[dataTypes.length - 1];
    newValues[dataTypes.length - 1]=sbRejectReason;
  }
 else {
    newValues[dataTypes.length]=sbRejectReason;
  }
  return new CleansedRowResult(RowFactory.create(newValues),columnsValid,rowValid);
}
