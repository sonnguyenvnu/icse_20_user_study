protected boolean checkValue(Object vx,Object vy){
  if (ExpressionUtils.isError(vx) || ExpressionUtils.isError(vy)) {
    return false;
  }
 else   if (ExpressionUtils.isNonBlankData(vx) && ExpressionUtils.isNonBlankData(vy)) {
    if (vx instanceof Number && vy instanceof Number) {
      double dx=((Number)vx).doubleValue();
      double dy=((Number)vy).doubleValue();
      return (!Double.isInfinite(dx) && !Double.isNaN(dx) && !Double.isInfinite(dy) && !Double.isNaN(dy) && checkValues(dx,dy));
    }
 else {
      return false;
    }
  }
 else {
    return false;
  }
}
