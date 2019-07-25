/** 
 * Check that the expression has enough numbers and variables to fit the requirements of the operators and functions, also check for only 1 result stored at the end of the evaluation.
 */
private void validate(List<Token> rpn){
  Stack<Integer> stack=new Stack<Integer>();
  stack.push(0);
  for (  final Token token : rpn) {
switch (token.type) {
case UNARY_OPERATOR:
      if (stack.peek() < 1) {
        throw new ExpressionException("Missing parameter(s) for operator " + token);
      }
    break;
case OPERATOR:
  if (stack.peek() < 2) {
    throw new ExpressionException("Missing parameter(s) for operator " + token);
  }
stack.set(stack.size() - 1,stack.peek() - 2 + 1);
break;
case FUNCTION:
net.sourceforge.plantuml.evalex.LazyFunction f=functions.get(token.surface.toUpperCase(Locale.ROOT));
if (f == null) {
throw new ExpressionException("Unknown function '" + token + "' at position " + (token.pos + 1));
}
int numParams=stack.pop();
if (!f.numParamsVaries() && numParams != f.getNumParams()) {
throw new ExpressionException("Function " + token + " expected " + f.getNumParams() + " parameters, got " + numParams);
}
if (stack.size() <= 0) {
throw new ExpressionException("Too many function calls, maximum scope exceeded");
}
stack.set(stack.size() - 1,stack.peek() + 1);
break;
case OPEN_PAREN:
stack.push(0);
break;
default :
stack.set(stack.size() - 1,stack.peek() + 1);
}
}
if (stack.size() > 1) {
throw new ExpressionException("Too many unhandled function parameter lists");
}
 else if (stack.peek() > 1) {
throw new ExpressionException("Too many numbers or variables");
}
 else if (stack.peek() < 1) {
throw new ExpressionException("Empty expression");
}
}
