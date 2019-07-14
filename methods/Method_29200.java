private double getPercentage(long aofCurrentSize,long aofBaseSize){
  if (aofBaseSize == 0) {
    return 0.0D;
  }
  String format=String.format("%.2f",(Double.valueOf(aofCurrentSize - aofBaseSize) * 100 / aofBaseSize));
  return Double.parseDouble(format);
}
