public SearchRequestBuilder fields(String fieldsCSV){
  Assert.isFalse(this.excludeSource,"Fields can't be requested because _source section is excluded");
  this.fields=fieldsCSV;
  return this;
}
