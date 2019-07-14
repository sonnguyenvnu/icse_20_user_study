protected String getBody(Context ctx){
  return templateEngine.process(this.template,ctx);
}
