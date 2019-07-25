public static double rotate(LValue x,LValue y,RValue angle) throws EvaluationException {
  final double f=angle.getValue();
  final double cosF=Math.cos(f);
  final double sinF=Math.sin(f);
  final double xOld=x.getValue();
  final double yOld=y.getValue();
  x.assign(xOld * cosF - yOld * sinF);
  y.assign(xOld * sinF + yOld * cosF);
  return 0.0;
}
