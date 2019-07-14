public static Boolean isNum(Field field){
  if (field == null) {
    return false;
  }
  if (Integer.class.equals(field.getType()) || int.class.equals(field.getType())) {
    return true;
  }
  if (Double.class.equals(field.getType()) || double.class.equals(field.getType())) {
    return true;
  }
  if (Long.class.equals(field.getType()) || long.class.equals(field.getType())) {
    return true;
  }
  if (BigDecimal.class.equals(field.getType())) {
    return true;
  }
  return false;
}
