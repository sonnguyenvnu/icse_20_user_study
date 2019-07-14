private Template createTemplate(final MessageContent messageContent) throws IOException {
  TemplateLoader templateLoader=createTemplateLoader(messageContent);
  Configuration cfg=createConfiguration(templateLoader,messageContent.getCharset());
  return cfg.getTemplate(TEMPLATE_NAME);
}
