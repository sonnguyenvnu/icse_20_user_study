public void setMessage(String message){
  this.message=parser.parseExpression(message,ParserContext.TEMPLATE_EXPRESSION);
}
