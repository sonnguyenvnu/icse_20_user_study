/** 
 * Use  {@link #of(byte[])} instead: this old name is ambiguous.
 */
@Deprecated public static Sha256Hash create(byte[] contents){
  return of(contents);
}
