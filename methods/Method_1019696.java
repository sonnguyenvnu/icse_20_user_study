private void save(FieldMetadata field,Writer writer,OutputStream out,boolean indent,boolean formatAsJavaString) throws IOException {
  if (indent) {
    write(LF,writer,out);
    write(TAB,writer,out);
  }
  write(XMLFieldsConstants.FIELD_TAG_START_ELT,writer,out);
  writeAttr(XMLFieldsConstants.NAME_ATTR,field.getFieldName(),formatAsJavaString,writer,out);
  writeAttr(XMLFieldsConstants.LIST_ATTR,field.isListType(),formatAsJavaString,writer,out);
  writeAttr(XMLFieldsConstants.IMAGE_NAME_ATTR,field.getImageName(),formatAsJavaString,writer,out);
  writeAttr(XMLFieldsConstants.SYNTAX_KIND_ATTR,field.getSyntaxKind(),formatAsJavaString,writer,out);
  write(">",writer,out);
  if (indent) {
    write(LF,writer,out);
    write(TAB,writer,out);
    write(TAB,writer,out);
  }
  write(XMLFieldsConstants.DESCRIPTION_START_ELT,writer,out);
  write(XMLFieldsConstants.START_CDATA,writer,out);
  if (field.getDescription() != null) {
    write(field.getDescription(),writer,out);
  }
  write(XMLFieldsConstants.END_CDATA,writer,out);
  write(XMLFieldsConstants.DESCRIPTION_END_ELT,writer,out);
  if (indent) {
    write(LF,writer,out);
    write(TAB,writer,out);
  }
  write(XMLFieldsConstants.FIELD_END_ELT,writer,out);
}
