public String pathValue(){
  if (value == null) {
    return path;
  }
  return path + '.' + value;
}
