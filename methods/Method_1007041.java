@Override public A head(){
  return prefix.match(One::value,two -> two.values()._1(),three -> three.values()._1(),four -> four.values()._1());
}
