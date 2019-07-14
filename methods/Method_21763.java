public static double[] String2DoubleArr(String paramer){
  String[] split=paramer.split(",");
  double[] ds=new double[split.length];
  for (int i=0; i < ds.length; i++) {
    ds[i]=Double.parseDouble(split[i].trim());
  }
  return ds;
}
