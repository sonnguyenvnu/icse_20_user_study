@PostMapping("/xml") public String readXml(@RequestBody JavaBean bean){
  return "Read from XML: " + bean;
}
