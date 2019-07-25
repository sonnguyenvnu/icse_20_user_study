/** 
 * Computes hash code on the bytes representation of this  {@code Data}, using the given hash function.
 * @param f the hash function to compute hash code using
 * @return the hash code of this {@code Data}'s bytes, computed by the given function
 */
default long hash(LongHashFunction f){
  return f.hash(bytes(),checkedRandomDataInputAccess(),offset(),size());
}
