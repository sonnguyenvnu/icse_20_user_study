@Benchmark public int sum(){
  int sum=0;
  for (  Key k : set) {
    sum+=k.hash;
  }
  return sum;
}
