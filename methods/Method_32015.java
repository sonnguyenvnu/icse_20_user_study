protected void assemble(Fields fields){
  if (getBase() == null) {
    super.assemble(fields);
    fields.era=ERA_FIELD;
    fields.monthOfYear=new BasicMonthOfYearDateTimeField(this,12);
    fields.months=fields.monthOfYear.getDurationField();
  }
}
