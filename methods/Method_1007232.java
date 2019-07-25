/** 
 * Parse the configuration from file.
 */
void parse(File file) throws ParseException {
  if (file == null) {
    LOG.warn("parsing NULL file, so fallback on default configuration!");
    return;
  }
  if (!file.exists()) {
    LOG.warn(String.format("parsing not existing file %s, so fallback on default configuration!",file.getAbsolutePath()));
    return;
  }
  try {
    Reader reader=Files.newBufferedReader(file.toPath(),StandardCharsets.UTF_8);
    parse(reader);
  }
 catch (  IOException fex) {
    LOG.warn("parsing not existing file {}, fallback on default configuration!",file.getAbsolutePath(),fex);
  }
}
