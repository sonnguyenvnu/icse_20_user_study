public RValue bind(Expression expression,boolean isLValue) throws ParserException {
  final RValue variable=expression.getVariable(name,isLValue);
  if (variable == null) {
    throw new ParserException(getPosition(),"Variable '" + name + "' not found");
  }
  return variable;
}
