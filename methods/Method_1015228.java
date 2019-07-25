public static PrimitiveIterator.OfInt masks(int bitmap){
  return new PrimitiveIterator.OfInt(){
    @Override public int nextInt(){
      int result=lowestBit(b);
      b&=~result;
      return result;
    }
    @Override public boolean hasNext(){
      return b != 0;
    }
  }
;
}
