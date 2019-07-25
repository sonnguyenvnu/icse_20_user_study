@Setup public void setup(){
  data=new double[10000000];
  for (int i=0; i < data.length; i++) {
    data[i]=gen.nextDouble();
  }
}
