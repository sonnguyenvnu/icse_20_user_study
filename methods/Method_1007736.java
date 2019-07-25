@Dynamic public static double gclosest(RValue x,RValue y,RValue z,RValue index,RValue count,RValue stride) throws EvaluationException {
  return findClosest(gmegabuf,x.getValue(),y.getValue(),z.getValue(),(int)index.getValue(),(int)count.getValue(),(int)stride.getValue());
}
