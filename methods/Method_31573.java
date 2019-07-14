private static String readVersion(){
  try {
    return FileCopyUtils.copyToString(VersionPrinter.class.getClassLoader().getResourceAsStream("org/flywaydb/core/internal/version.txt"),StandardCharsets.UTF_8);
  }
 catch (  IOException e) {
    throw new FlywayException("Unable to read Flyway version: " + e.getMessage(),e);
  }
}
