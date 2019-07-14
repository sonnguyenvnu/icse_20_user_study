@Override public Choice<Unifier> visitBinary(BinaryTree binary,Unifier unifier){
  return Choice.condition(getKind().equals(binary.getKind()),unifier).thenChoose(unifications(getLeftOperand(),binary.getLeftOperand())).thenChoose(unifications(getRightOperand(),binary.getRightOperand()));
}
