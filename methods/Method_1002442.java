/** 
 * Read an  {@link InputStream} and parse the {@link InputStream} looking for thespecified name.
 * @param inputStream to parse.
 * @param location of the input source.
 * @param name to locate.
 * @param errorMessageBuilder to append error messages to.
 * @return the {@link NamedDataSchema} is found in the input stream, else return null.
 */
protected NamedDataSchema parse(InputStream inputStream,final DataSchemaLocation location,String name,StringBuilder errorMessageBuilder){
  NamedDataSchema schema=null;
  PegasusSchemaParser parser=_parserFactory.create(_dependencyResolver);
  parser.setLocation(location);
  parser.parse(new FilterInputStream(inputStream){
    @Override public String toString(){
      return location.toString();
    }
  }
);
  if (parser.hasError()) {
    errorMessageBuilder.append("Error parsing ").append(location).append(" for \"").append(name).append("\".\n");
    errorMessageBuilder.append(parser.errorMessageBuilder());
    errorMessageBuilder.append("Done parsing ").append(location).append(".\n");
    _badLocations.add(location);
  }
 else {
    DataSchema found=_nameToDataSchema.get(name);
    if (found != null && found instanceof NamedDataSchema) {
      schema=(NamedDataSchema)found;
    }
  }
  return schema;
}
