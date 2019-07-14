private void resetListeners(){
  lexer.removeErrorListeners();
  lexer.addErrorListener(new ErrorHandler());
}
