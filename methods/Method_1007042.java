@Override public A last(){
  return suffix.match(One::value,two -> two.values()._2(),three -> three.values()._3(),four -> four.values()._4());
}
