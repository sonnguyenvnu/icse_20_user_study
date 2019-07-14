public boolean isActualValue(){
  return isLowerBounded() && isUpperBounded() && lower.equals(upper);
}
