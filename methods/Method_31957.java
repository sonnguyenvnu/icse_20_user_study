protected void assemble(Fields fields){
  if (getBase() == null) {
    super.assemble(fields);
    fields.year=new SkipDateTimeField(this,fields.year);
    fields.weekyear=new SkipDateTimeField(this,fields.weekyear);
    fields.era=ERA_FIELD;
    fields.monthOfYear=new BasicMonthOfYearDateTimeField(this,13);
    fields.months=fields.monthOfYear.getDurationField();
  }
}
