protected String getSubject(Context ctx){
  return templateEngine.process(this.template,singleton("subject"),ctx).trim();
}
