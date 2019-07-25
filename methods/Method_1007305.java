private static BadBytecode error(String sig){
  return new BadBytecode("bad signature: " + sig);
}
