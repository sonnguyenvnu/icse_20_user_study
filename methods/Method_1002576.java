private static String encode(DataSchema schema,String format) throws IOException {
  if (format.equals(PdlSchemaParser.FILETYPE)) {
    StringWriter writer=new StringWriter();
    SchemaToPdlEncoder encoder=new SchemaToPdlEncoder(writer);
    encoder.setTypeReferenceFormat(AbstractSchemaEncoder.TypeReferenceFormat.PRESERVE);
    encoder.encode(schema);
    return writer.toString();
  }
 else   if (format.equals(SchemaParser.FILETYPE)) {
    JsonBuilder.Pretty pretty=JsonBuilder.Pretty.INDENTED;
    JsonBuilder builder=new JsonBuilder(pretty);
    try {
      SchemaToJsonEncoder encoder=new SchemaToJsonEncoder(builder,AbstractSchemaEncoder.TypeReferenceFormat.PRESERVE);
      encoder.encode(schema);
      return builder.result();
    }
  finally {
      builder.closeQuietly();
    }
  }
 else {
    throw new IllegalArgumentException("Unsupported format: " + format);
  }
}
