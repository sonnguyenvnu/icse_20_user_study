public boolean matchDelim(Node open,Node close){
  return (open instanceof Delimeter && close instanceof Delimeter && matchString(((Delimeter)open).shape,((Delimeter)close).shape));
}
