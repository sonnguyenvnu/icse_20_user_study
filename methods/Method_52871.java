private boolean isJsonParse(ASTText prevText){
  final String text=prevText.getImage().endsWith("'") ? prevText.getImage().substring(0,prevText.getImage().length() - 1) : prevText.getImage();
  return text.endsWith("JSON.parse(") || text.endsWith("jQuery.parseJSON(") || text.endsWith("$.parseJSON(");
}
