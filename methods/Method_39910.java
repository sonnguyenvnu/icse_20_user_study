public void setTest(final String test){
  try {
    this.testValue=Converter.get().toBooleanValue(test,false);
  }
 catch (  TypeConversionException ignore) {
    this.testValue=false;
  }
}
