@Dynamic public static double megabuf(RValue index) throws EvaluationException {
  return getBufferItem(Expression.getInstance().getFunctions().megabuf,(int)index.getValue());
}
