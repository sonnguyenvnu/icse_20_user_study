/** 
 * <term> := <term-start> ( <path-segment> ) <term-start> := <string> | <number> | - <number> | <regex> | <identifier> | <identifier> ( <expression-list> ) <path-segment> := "[" <expression-list> "]" | "." <identifier> | "." <identifier> "(" <expression-list> ")"
 */
protected Evaluable parseFactor() throws ParsingException {
  if (_token == null) {
    throw makeException("Expecting something more at end of expression");
  }
  Evaluable eval=null;
  if (_token.type == TokenType.String) {
    eval=new LiteralExpr(_token.text);
    next(false);
  }
 else   if (_token.type == TokenType.Regex) {
    RegexToken t=(RegexToken)_token;
    try {
      Pattern pattern=Pattern.compile(_token.text,t.caseInsensitive ? Pattern.CASE_INSENSITIVE : 0);
      eval=new LiteralExpr(pattern);
      next(false);
    }
 catch (    Exception e) {
      throw makeException("Bad regular expression (" + e.getMessage() + ")");
    }
  }
 else   if (_token.type == TokenType.Number) {
    eval=new LiteralExpr(((NumberToken)_token).value);
    next(false);
  }
 else   if (_token.type == TokenType.Operator && _token.text.equals("-")) {
    next(true);
    if (_token != null && _token.type == TokenType.Number) {
      Number n=((NumberToken)_token).value;
      eval=new LiteralExpr(n instanceof Long ? -n.longValue() : -n.doubleValue());
      next(false);
    }
 else {
      throw makeException("Bad negative number");
    }
  }
 else   if (_token.type == TokenType.Identifier) {
    String text=_token.text;
    next(false);
    if (_token == null || _token.type != TokenType.Delimiter || !_token.text.equals("(")) {
      eval="null".equals(text) ? new LiteralExpr(null) : new VariableExpr(text);
    }
 else     if ("PI".equals(text)) {
      eval=new LiteralExpr(Math.PI);
      next(false);
    }
 else {
      Function f=ControlFunctionRegistry.getFunction(text);
      Control c=ControlFunctionRegistry.getControl(text);
      if (f == null && c == null) {
        throw makeException("Unknown function or control named " + text);
      }
      next(true);
      List<Evaluable> args=parseExpressionList(")");
      if (c != null) {
        Evaluable[] argsA=makeArray(args);
        String errorMessage=c.checkArguments(argsA);
        if (errorMessage != null) {
          throw makeException(errorMessage);
        }
        eval=new ControlCallExpr(argsA,c);
      }
 else {
        eval=new FunctionCallExpr(makeArray(args),f);
      }
    }
  }
 else   if (_token.type == TokenType.Delimiter && _token.text.equals("(")) {
    next(true);
    eval=parseExpression();
    if (_token != null && _token.type == TokenType.Delimiter && _token.text.equals(")")) {
      next(false);
    }
 else {
      throw makeException("Missing )");
    }
  }
 else   if (_token.type == TokenType.Delimiter && _token.text.equals("[")) {
    next(true);
    List<Evaluable> args=parseExpressionList("]");
    eval=new FunctionCallExpr(makeArray(args),new ArgsToArray());
  }
 else {
    throw makeException("Missing number, string, identifier, regex, or parenthesized expression");
  }
  while (_token != null) {
    if (_token.type == TokenType.Operator && _token.text.equals(".")) {
      next(false);
      if (_token == null || _token.type != TokenType.Identifier) {
        throw makeException("Missing function name");
      }
      String identifier=_token.text;
      next(false);
      if (_token != null && _token.type == TokenType.Delimiter && _token.text.equals("(")) {
        next(true);
        Function f=ControlFunctionRegistry.getFunction(identifier);
        if (f == null) {
          throw makeException("Unknown function " + identifier);
        }
        List<Evaluable> args=parseExpressionList(")");
        args.add(0,eval);
        eval=new FunctionCallExpr(makeArray(args),f);
      }
 else {
        eval=new FieldAccessorExpr(eval,identifier);
      }
    }
 else     if (_token.type == TokenType.Delimiter && _token.text.equals("[")) {
      next(true);
      List<Evaluable> args=parseExpressionList("]");
      args.add(0,eval);
      eval=new FunctionCallExpr(makeArray(args),ControlFunctionRegistry.getFunction("get"));
    }
 else {
      break;
    }
  }
  return eval;
}
