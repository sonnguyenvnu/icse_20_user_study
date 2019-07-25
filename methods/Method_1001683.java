public double cost(Collection<Link> links){
  double result=0;
  for (  Link link : links) {
    result+=getLength(link);
  }
  return result;
}
