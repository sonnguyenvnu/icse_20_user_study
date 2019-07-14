private DateTimeField convertField(DateTimeField field,HashMap<Object,Object> converted){
  if (field == null || !field.isSupported()) {
    return field;
  }
  if (converted.containsKey(field)) {
    return (DateTimeField)converted.get(field);
  }
  LimitDateTimeField limitField=new LimitDateTimeField(field,convertField(field.getDurationField(),converted),convertField(field.getRangeDurationField(),converted),convertField(field.getLeapDurationField(),converted));
  converted.put(field,limitField);
  return limitField;
}
