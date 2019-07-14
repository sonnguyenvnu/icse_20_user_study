public boolean hasEntity(){
  return (asList(PUT,PATCH,POST).contains(this));
}
