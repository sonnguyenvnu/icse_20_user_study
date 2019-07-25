default Expression negate(){
  throw getContext().error(String.format("%s: unsupported unary operator: -",this));
}
