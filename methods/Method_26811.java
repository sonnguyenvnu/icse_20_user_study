@PostMapping("/data/standard/request/reader") public String requestReader(Reader requestBodyReader) throws IOException {
  return "Read char request body = " + FileCopyUtils.copyToString(requestBodyReader);
}
