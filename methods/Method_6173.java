void ftyp(MP4Atom atom) throws IOException {
  if (LOGGER.isLoggable(debugLevel)) {
    LOGGER.log(debugLevel,atom.toString());
  }
  brand=atom.readString(4,ASCII).trim();
  if (brand.matches("M4V|MP4|mp42|isom")) {
    LOGGER.warning(atom.getPath() + ": brand=" + brand + " (experimental)");
  }
 else   if (!brand.matches("M4A|M4P")) {
    LOGGER.warning(atom.getPath() + ": brand=" + brand + " (expected M4A or M4P)");
  }
  version=String.valueOf(atom.readInt());
}
