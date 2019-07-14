public static double[] KV2DoubleArr(List<KVValue> params){
  double[] ds=new double[params.size()];
  int i=0;
  for (  KVValue v : params) {
    ds[i]=((Number)v.value).doubleValue();
    i++;
  }
  return ds;
}
