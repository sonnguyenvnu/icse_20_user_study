String check(final ICoverageNode node){
  final double d=node.getCounter(entity).getValue(value);
  if (Double.isNaN(d)) {
    return null;
  }
  final BigDecimal bd=BigDecimal.valueOf(d);
  if (minimum != null && minimum.compareTo(bd) > 0) {
    return message("minimum",bd,minimum,RoundingMode.FLOOR);
  }
  if (maximum != null && maximum.compareTo(bd) < 0) {
    return message("maximum",bd,maximum,RoundingMode.CEILING);
  }
  return null;
}
