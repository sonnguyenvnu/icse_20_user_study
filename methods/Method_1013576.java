/** 
 * This method returns the value permuted by the permutation. It returns this if nothing is permuted.
 */
@Override public IValue permute(IMVPerm perm){
  try {
    Assert.fail("TLC has found a state in which the value of a variable contains " + Values.ppr(this.toString()));
    return null;
  }
 catch (  RuntimeException|OutOfMemoryError e) {
    if (hasSource()) {
      throw FingerprintException.getNewHead(this,e);
    }
 else {
      throw e;
    }
  }
}
