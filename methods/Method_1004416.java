public static void gauge(String name,String[] tags,String[] values,Supplier<Double> supplier){
  INSTANCE.newGauge(name,tags,values,supplier);
}
