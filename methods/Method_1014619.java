public void load(Document document){
  LogManager.LOGGER.fine(String.format("(%s) Loading from database",name));
  if (!version.equals(document.getString("version"))) {
    LogManager.LOGGER.warning(String.format("(%s) Version mismatch with database!" + " This could cause problems. %s!=%s",name,version,document.getString("version")));
  }
}
