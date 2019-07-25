public BitVectorWrapper call() throws Exception {
  try {
    BitVector bv=fpset.get(index).containsBlock(fps[index]);
    return new BitVectorWrapper(index,bv);
  }
 catch (  Exception e) {
    return reassign(e);
  }
}
