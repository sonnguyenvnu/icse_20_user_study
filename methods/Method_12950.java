public static Object convert(String value,Field field,String format,boolean us){
  if (!StringUtils.isEmpty(value)) {
    if (Float.class.equals(field.getType())) {
      return Float.parseFloat(value);
    }
    if (Integer.class.equals(field.getType()) || int.class.equals(field.getType())) {
      return Integer.parseInt(value);
    }
    if (Double.class.equals(field.getType()) || double.class.equals(field.getType())) {
      if (null != format && !"".equals(format)) {
        int n=getCountOfChar(value,'0');
        return Double.parseDouble(TypeUtil.formatFloat0(value,n));
      }
 else {
        return Double.parseDouble(TypeUtil.formatFloat(value));
      }
    }
    if (Boolean.class.equals(field.getType()) || boolean.class.equals(field.getType())) {
      String valueLower=value.toLowerCase();
      if (valueLower.equals("true") || valueLower.equals("false")) {
        return Boolean.parseBoolean(value.toLowerCase());
      }
      Integer integer=Integer.parseInt(value);
      if (integer == 0) {
        return false;
      }
 else {
        return true;
      }
    }
    if (Long.class.equals(field.getType()) || long.class.equals(field.getType())) {
      return Long.parseLong(value);
    }
    if (Date.class.equals(field.getType())) {
      if (value.contains("-") || value.contains("/") || value.contains(":")) {
        return getSimpleDateFormatDate(value,format);
      }
 else {
        Double d=Double.parseDouble(value);
        return HSSFDateUtil.getJavaDate(d,us);
      }
    }
    if (BigDecimal.class.equals(field.getType())) {
      return new BigDecimal(value);
    }
    if (String.class.equals(field.getType())) {
      return formatFloat(value);
    }
  }
  return null;
}
