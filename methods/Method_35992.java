public MultipartValuePatternBuilder withName(String name){
  this.name=name;
  return withHeader("Content-Disposition",containing("name=\"" + name + "\""));
}
