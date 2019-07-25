@At(path="/tag/{id}",types={RestRequest.Method.GET}) public void find(){
  Tag tag=Tag.where(map("name",param("id"))).singleFetch();
  if (tag == null) {
    render(404,map());
  }
  render(404,list(tag));
}
