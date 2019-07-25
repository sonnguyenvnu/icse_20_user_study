public static String encode(Base b,byte[] data){
switch (b) {
case Base58BTC:
    return b.prefix + Base58.encode(data);
case Base16:
  return b.prefix + Base16.encode(data);
case Base32:
return b.prefix + new String(new Base32().encode(data)).toLowerCase().replaceAll("=","");
case Base32Upper:
return b.prefix + new String(new Base32().encode(data)).replaceAll("=","");
case Base32Hex:
return b.prefix + new String(new Base32(true).encode(data)).toLowerCase().replaceAll("=","");
case Base32HexUpper:
return b.prefix + new String(new Base32(true).encode(data)).replaceAll("=","");
default :
throw new IllegalStateException("Unsupported base encoding: " + b.name());
}
}
