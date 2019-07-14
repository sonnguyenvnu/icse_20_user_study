protected void computeFeatures(){
  quoteFieldNames=(this.features & SerializerFeature.QuoteFieldNames.mask) != 0;
  useSingleQuotes=(this.features & SerializerFeature.UseSingleQuotes.mask) != 0;
  sortField=(this.features & SerializerFeature.SortField.mask) != 0;
  disableCircularReferenceDetect=(this.features & SerializerFeature.DisableCircularReferenceDetect.mask) != 0;
  beanToArray=(this.features & SerializerFeature.BeanToArray.mask) != 0;
  writeNonStringValueAsString=(this.features & SerializerFeature.WriteNonStringValueAsString.mask) != 0;
  notWriteDefaultValue=(this.features & SerializerFeature.NotWriteDefaultValue.mask) != 0;
  writeEnumUsingName=(this.features & SerializerFeature.WriteEnumUsingName.mask) != 0;
  writeEnumUsingToString=(this.features & SerializerFeature.WriteEnumUsingToString.mask) != 0;
  writeDirect=quoteFieldNames && (this.features & nonDirectFeatures) == 0 && (beanToArray || writeEnumUsingName);
  keySeperator=useSingleQuotes ? '\'' : '"';
  browserSecure=(this.features & SerializerFeature.BrowserSecure.mask) != 0;
  final long S0=0x4FFFFFFFFL, S1=0x8004FFFFFFFFL, S2=0x50000304ffffffffL;
  sepcialBits=browserSecure ? S2 : (features & SerializerFeature.WriteSlashAsSpecial.mask) != 0 ? S1 : S0;
}
