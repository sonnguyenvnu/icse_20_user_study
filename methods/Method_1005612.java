private void fail(String reason){
  String message=String.format("ERROR in %s.%s: %s",method.getDefiningClass().toHuman(),method.getNat().toHuman(),reason);
  throw new SimException(message);
}
