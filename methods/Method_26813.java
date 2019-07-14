@GetMapping("/data/standard/response/writer") public void availableStandardResponseArguments(Writer responseWriter) throws IOException {
  responseWriter.write("Wrote char response using Writer");
}
