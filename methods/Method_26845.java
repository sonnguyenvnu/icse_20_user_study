@GetMapping("/entity/headers") public ResponseEntity<String> responseEntityCustomHeaders(){
  HttpHeaders headers=new HttpHeaders();
  headers.setContentType(MediaType.TEXT_PLAIN);
  return new ResponseEntity<String>("The String ResponseBody with custom header Content-Type=text/plain",headers,HttpStatus.OK);
}
