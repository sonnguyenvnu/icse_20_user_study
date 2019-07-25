public LazyString map(F<Character,Character> f){
  return fromStream(s.map(f));
}
