private void makeOutputDir() throws Exception {
  if (!OUTPUT_DIR.exists()) {
    OUTPUT_DIR.mkdirs();
    info("Created directory: " + OUTPUT_DIR.getAbsolutePath());
  }
}
