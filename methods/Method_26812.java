@PostMapping("/data/standard/request/is") public String requestReader(InputStream requestBodyIs) throws IOException {
  return "Read binary request body = " + new String(FileCopyUtils.copyToByteArray(requestBodyIs));
}
