/** 
 * Serialize as XML the given  {@link FieldsMetadata} to the given XML output stream. Here a sample of XML writer :<pre> <fields> <field name="project.Name" imageName="" listType="false" /> <field name="developers.Name" imageName="" listType="true" /> <field name="project.Logo" imageName="Logo" listType="false" /> </fields> </pre>
 * @param fieldsMetadata the metadata to serialize to XML.
 * @param writer the writer (null if outputstream is not null).
 * @param outputstream the output steam (null if writer is not null).
 * @param indent true if indent must be managed and false otherwise.
 * @param formatAsJavaString true if format as Java String to be done and false otherwise. * @throws IOException
 */
private void save(FieldsMetadata fieldsMetadata,Writer writer,OutputStream out,boolean indent,boolean formatAsJavaString) throws IOException {
  Collection<FieldMetadata> fields=fieldsMetadata.getFields();
  if (formatAsJavaString) {
    write("\"",writer,out);
    write(XMLFieldsConstants.XML_DECLARATION_AS_JAVA_STRING,writer,out);
  }
 else {
    write(XMLFieldsConstants.XML_DECLARATION,writer,out);
  }
  if (indent) {
    write(LF,writer,out);
  }
  write(XMLFieldsConstants.FIELDS_TAG_START_ELT,writer,out);
  writeAttr(XMLFieldsConstants.TEMPLATE_ENGINE_KIND_ATTR,fieldsMetadata.getTemplateEngineKind(),formatAsJavaString,writer,out);
  write(" >",writer,out);
  if (indent) {
    write(LF,writer,out);
    write(TAB,writer,out);
  }
  write(XMLFieldsConstants.DESCRIPTION_START_ELT,writer,out);
  write(XMLFieldsConstants.START_CDATA,writer,out);
  if (fieldsMetadata.getDescription() != null) {
    write(fieldsMetadata.getDescription(),writer,out);
  }
  write(XMLFieldsConstants.END_CDATA,writer,out);
  write(XMLFieldsConstants.DESCRIPTION_END_ELT,writer,out);
  for (  FieldMetadata field : fields) {
    save(field,writer,out,indent,formatAsJavaString);
  }
  if (indent) {
    write(LF,writer,out);
  }
  write(XMLFieldsConstants.FIELDS_END_ELT,writer,out);
  if (formatAsJavaString) {
    write("\"",writer,out);
  }
}
