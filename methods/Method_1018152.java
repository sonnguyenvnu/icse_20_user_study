public static void main(String... args){
  if (args.length < 2) {
    System.out.println("Usage: <password text> <hash algorithm> [<encoding>]");
    System.exit(1);
  }
  String value=args[0];
  String alg=args[1];
  String encoding=args.length >= 3 ? args[2].toLowerCase() : "hex";
  if (!encoding.equals("base64") && !encoding.equals("hex")) {
    System.err.println("Only supported encodings for this tool are \"base64\" or \"hex\"");
    System.exit(2);
  }
  try {
    MessageDigest md=MessageDigest.getInstance(alg);
    byte[] bytes=md.digest(value.getBytes());
    System.out.println(encoding.equals("hex") ? DatatypeConverter.printHexBinary(bytes).toLowerCase() : DatatypeConverter.printBase64Binary(bytes));
  }
 catch (  NoSuchAlgorithmException e) {
    System.err.println("Unknown hash algorithm: " + e.getMessage());
    System.exit(3);
  }
}
