@Override public Fp dbl(){
  return new Fp(v.add(v).mod(P));
}
