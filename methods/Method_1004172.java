/** 
 * Compares bytes of this  {@code Data} to the given bytes {@code source}, starting from the given offset. <p> <p>Default implementation compares  {@link #bytes()} of this {@code Data}, but custom implementation may only check if  {@linkplain #get() object} of this {@code Data} <i>would</i>be serialized to the same bytes sequence, if this  {@code Data} wraps an object and obtaining{@link #bytes()} requires serialization internally.
 * @param source       the bytes source, to compare this {@code Data}'s bytes with
 * @param sourceOffset the offset in the bytes source, the bytes sequence starts from
 * @return {@code true} if the given bytes sequence is equivalent to this {@code Data}'s bytes, byte-by-byte
 */
default boolean equivalent(RandomDataInput source,long sourceOffset){
  return BytesUtil.bytesEqual(source,sourceOffset,bytes(),offset(),size());
}
