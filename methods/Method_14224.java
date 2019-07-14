static private void adjustLegacyOptions(String format,Properties parameters,ObjectNode optionObj){
  if (",".equals(parameters.getProperty("separator"))) {
    JSONUtilities.safePut(optionObj,"separator",",");
  }
 else   if ("\\t".equals(parameters.getProperty("separator"))) {
    JSONUtilities.safePut(optionObj,"separator","\t");
  }
  adjustLegacyIntegerOption(format,parameters,optionObj,"ignore","ignoreLines");
  adjustLegacyIntegerOption(format,parameters,optionObj,"header-lines","headerLines");
  adjustLegacyIntegerOption(format,parameters,optionObj,"skip","skipDataLines");
  adjustLegacyIntegerOption(format,parameters,optionObj,"limit","limit");
  adjustLegacyBooleanOption(format,parameters,optionObj,"guess-value-type","guessCellValueTypes",false);
  adjustLegacyBooleanOption(format,parameters,optionObj,"ignore-quotes","processQuotes",true);
}
