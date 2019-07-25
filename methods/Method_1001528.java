public String decode(String code) throws IOException {
  if (code.startsWith("0A")) {
    return zlibBase64.decode(code.substring(2));
  }
  if (code.startsWith("0B")) {
    return brotliBase64.decode(code.substring(2));
  }
  if (code.startsWith("0C")) {
    return base64only.decode(code.substring(2));
  }
  if (code.startsWith("0D")) {
    return hexOnly.decode(code.substring(2));
  }
  if (code.startsWith("-deflate-")) {
    return zlibBase64.decode(code.substring("-deflate-".length()));
  }
  if (code.startsWith("-brotli-")) {
    return brotliBase64.decode(code.substring("-brotli-".length()));
  }
  if (code.startsWith("-base64-")) {
    return base64only.decode(code.substring("-base64-".length()));
  }
  if (code.startsWith("-hex-")) {
    return hexOnly.decode(code.substring("-hex-".length()));
  }
  if (code.startsWith("0")) {
    return brotli.decode(code.substring(1));
  }
  try {
    return zlib.decode(code);
  }
 catch (  Exception ex) {
    return oldOne.decode(code);
  }
}
