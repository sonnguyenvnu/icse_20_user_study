@Dynamic public static double megabuf(RValue index,double value) throws EvaluationException {
  return setBufferItem(Expression.getInstance().getFunctions().megabuf,(int)index.getValue(),value);
}
