public static PublicKeyHash decode(byte[] raw){
  return new PublicKeyHash(Cid.cast(raw));
}
