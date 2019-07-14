private Configuration createConfiguration(final TemplateLoader templateLoader,final Charset charset){
  Configuration cfg=new Configuration(CURRENT_VERSION);
  cfg.setObjectWrapper(new DefaultObjectWrapperBuilder(CURRENT_VERSION).build());
  cfg.setDefaultEncoding(charset.name());
  cfg.setTemplateLoader(templateLoader);
  return cfg;
}
