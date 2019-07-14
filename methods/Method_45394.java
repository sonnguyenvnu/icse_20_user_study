private Resource asResource(final String name,final FileContainer fileContainer){
  Optional<Charset> charset=fileContainer.getCharset();
  String text=fileContainer.getName().getText();
  return asResource(name,text(text),charset);
}
