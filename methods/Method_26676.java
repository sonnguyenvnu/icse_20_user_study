@Override public Choice<Unifier> visitLiteral(LiteralTree literal,Unifier unifier){
  return Choice.condition(match(getValue(),literal.getValue()),unifier);
}
