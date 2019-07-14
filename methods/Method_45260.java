public Object getJson(){
  try {
    return Jsons.toObject(this.request.getContent().toString(),Object.class);
  }
 catch (  Exception e) {
    throw new IllegalArgumentException("Json content is expected",e);
  }
}
