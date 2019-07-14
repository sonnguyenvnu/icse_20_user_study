public static RequestPattern everything(){
  return newRequestPattern(RequestMethod.ANY,anyUrl()).build();
}
