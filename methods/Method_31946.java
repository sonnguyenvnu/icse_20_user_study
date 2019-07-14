protected void assemble(Fields fields){
  if (getParam() == null) {
    fields.eras=UnsupportedDurationField.getInstance(DurationFieldType.eras());
    DateTimeField field=fields.year;
    fields.year=new OffsetDateTimeField(new SkipUndoDateTimeField(this,field),BUDDHIST_OFFSET);
    field=fields.yearOfEra;
    fields.yearOfEra=new DelegatedDateTimeField(fields.year,fields.eras,DateTimeFieldType.yearOfEra());
    field=fields.weekyear;
    fields.weekyear=new OffsetDateTimeField(new SkipUndoDateTimeField(this,field),BUDDHIST_OFFSET);
    field=new OffsetDateTimeField(fields.yearOfEra,99);
    fields.centuryOfEra=new DividedDateTimeField(field,fields.eras,DateTimeFieldType.centuryOfEra(),100);
    fields.centuries=fields.centuryOfEra.getDurationField();
    field=new RemainderDateTimeField((DividedDateTimeField)fields.centuryOfEra);
    fields.yearOfCentury=new OffsetDateTimeField(field,DateTimeFieldType.yearOfCentury(),1);
    field=new RemainderDateTimeField(fields.weekyear,fields.centuries,DateTimeFieldType.weekyearOfCentury(),100);
    fields.weekyearOfCentury=new OffsetDateTimeField(field,DateTimeFieldType.weekyearOfCentury(),1);
    fields.era=ERA_FIELD;
  }
}
