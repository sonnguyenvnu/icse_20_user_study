protected void assemble(Fields fields){
  if (getBase() == null) {
    super.assemble(fields);
    fields.year=new SkipDateTimeField(this,fields.year);
    fields.weekyear=new SkipDateTimeField(this,fields.weekyear);
  }
}
