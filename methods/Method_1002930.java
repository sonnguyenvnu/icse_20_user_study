boolean contains(BuiltInProperty e){
  return (elements & mask(e)) != 0;
}
