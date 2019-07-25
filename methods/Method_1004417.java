public static void gauge(String name,Supplier<Double> supplier){
  INSTANCE.newGauge(name,EMPTY,EMPTY,supplier);
}
