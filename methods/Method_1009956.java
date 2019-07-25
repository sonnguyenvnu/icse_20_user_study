@Setup public void setup(){
  data=new double[10000000];
  for (int i=0; i < data.length; i++) {
    data[i]=gen.nextDouble();
  }
  if (method.equals("tree")) {
    td=new AVLTreeDigest(compression);
  }
 else {
    td=new MergingDigest(500);
  }
  for (int i=0; i < 5 * compression; ++i) {
    td.add(gen.nextDouble());
  }
}
