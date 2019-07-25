@Setup public void setup(){
  data=new double[10000000];
  for (int i=0; i < data.length; i++) {
    data[i]=gen.nextDouble();
  }
  fh=new FloatHistogram(0.1,10000,binsPerDecade);
  for (int i=0; i < 10000; ++i) {
    fh.add(gen.nextDouble());
  }
}
