public SearchRequestBuilder resource(Resource resource){
  this.indices=resource.index();
  if (resource.isTyped()) {
    this.types=resource.type();
  }
 else {
    this.types=null;
  }
  return this;
}
