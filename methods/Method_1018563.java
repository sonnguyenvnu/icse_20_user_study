public static Cid decode(String v){
  if (v.length() < 2)   throw new IllegalStateException("Cid too short!");
  if (v.length() == 46 && v.startsWith("Qm"))   return buildV0(Multihash.fromBase58(v));
  byte[] data=Multibase.decode(v);
  return cast(data);
}
