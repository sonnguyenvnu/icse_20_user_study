@Nonnull @Override public TemplateDTO create(@Nullable final String name,@Nonnull final String xml){
  final FormDataBodyPart templatePart=new FormDataBodyPart("template",xml,MediaType.APPLICATION_OCTET_STREAM_TYPE);
  FormDataContentDisposition.FormDataContentDispositionBuilder disposition=FormDataContentDisposition.name(templatePart.getName());
  disposition.fileName((name == null) ? "import_template_" + System.currentTimeMillis() : name);
  templatePart.setFormDataContentDisposition(disposition.build());
  MultiPart multiPart=new MultiPart();
  multiPart.bodyPart(templatePart);
  multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
  return upload(multiPart);
}
