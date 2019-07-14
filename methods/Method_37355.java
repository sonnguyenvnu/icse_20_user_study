@Benchmark public String stringBand3(){
  return new StringBand(3).append(strings[1]).append(strings[2]).append(strings[3]).toString();
}
