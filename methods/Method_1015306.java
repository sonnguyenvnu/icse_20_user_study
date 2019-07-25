public void error(final String msg){
  insertDocument(String.format("<div class=\"error\">%s</div>",msg));
}
