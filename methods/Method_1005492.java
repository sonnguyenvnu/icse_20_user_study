public void build(DozerBuilder.FieldDefinitionBuilder builder){
  builder.accessible(this.accessible);
  builder.createMethod(this.createMethod);
  builder.key(this.key);
  builder.mapGetMethod(this.mapGetMethod);
  builder.mapSetMethod(this.mapSetMethod);
  builder.theGetMethod(this.getMethod);
  builder.theSetMethod(this.setMethod);
  if (this.iterate) {
    builder.iterate();
  }
}
