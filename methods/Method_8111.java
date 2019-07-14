public String getLink(int num){
  if (num < 0 || num >= links.size()) {
    return null;
  }
  return links.get(num);
}
