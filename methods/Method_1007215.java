public final P2<Rng,Integer> range(int low,int high){
  return nextNatural().map2(x -> (x % (high - low + 1)) + low);
}
