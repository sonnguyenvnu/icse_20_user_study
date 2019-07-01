@Override public void _XXXXX_(final BufferedImage src,final OutputStream os,final Map<String,Object> params) throws ImageWriteException, IOException {
  os.write(PnmConstants.PNM_PREFIX_BYTE);
  os.write(PnmConstants.PAM_RAW_CODE);
  os.write(PnmConstants.PNM_NEWLINE);
  final int width=src.getWidth();
  final int height=src.getHeight();
  os.write(("WIDTH " + width).getBytes(StandardCharsets.US_ASCII));
  os.write(PnmConstants.PNM_NEWLINE);
  os.write(("HEIGHT " + height).getBytes(StandardCharsets.US_ASCII));
  os.write(PnmConstants.PNM_NEWLINE);
  os.write(("DEPTH 4").getBytes(StandardCharsets.US_ASCII));
  os.write(PnmConstants.PNM_NEWLINE);
  os.write(("MAXVAL 255").getBytes(StandardCharsets.US_ASCII));
  os.write(PnmConstants.PNM_NEWLINE);
  os.write(("TUPLTYPE RGB_ALPHA").getBytes(StandardCharsets.US_ASCII));
  os.write(PnmConstants.PNM_NEWLINE);
  os.write(("ENDHDR").getBytes(StandardCharsets.US_ASCII));
  os.write(PnmConstants.PNM_NEWLINE);
  for (int y=0; y < height; y++) {
    for (int x=0; x < width; x++) {
      final int argb=src.getRGB(x,y);
      final int alpha=0xff & (argb >> 24);
      final int red=0xff & (argb >> 16);
      final int green=0xff & (argb >> 8);
      final int blue=0xff & (argb >> 0);
      os.write((byte)red);
      os.write((byte)green);
      os.write((byte)blue);
      os.write((byte)alpha);
    }
  }
}