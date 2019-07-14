@GetMapping("{path}/simple") public String withMatrixVariable(@PathVariable String path,@MatrixVariable String foo){
  return "Obtained matrix variable 'foo=" + foo + "' from path segment '" + path + "'";
}
