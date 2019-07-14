protected void assemble(Fields fields){
  if (getBase().getZone() == DateTimeZone.UTC) {
    fields.centuryOfEra=new DividedDateTimeField(ISOYearOfEraDateTimeField.INSTANCE,DateTimeFieldType.centuryOfEra(),100);
    fields.centuries=fields.centuryOfEra.getDurationField();
    fields.yearOfCentury=new RemainderDateTimeField((DividedDateTimeField)fields.centuryOfEra,DateTimeFieldType.yearOfCentury());
    fields.weekyearOfCentury=new RemainderDateTimeField((DividedDateTimeField)fields.centuryOfEra,fields.weekyears,DateTimeFieldType.weekyearOfCentury());
  }
}
