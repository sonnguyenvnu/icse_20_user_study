public String writeArchive() throws IOException {
  String zipFilename;
  writeHabits();
  zipFilename=writeZipFile();
  cleanup();
  return zipFilename;
}
