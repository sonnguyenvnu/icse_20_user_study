@Benchmark public String stringBand2(){
  return new StringBand(2).append(strings[1]).append(strings[2]).toString();
}
