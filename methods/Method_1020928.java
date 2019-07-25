@At(path="/tag",types={RestRequest.Method.POST}) @Parameters(@Parameter(name="name",required=true,description="?????")) @Responses(@ApiResponse(responseCode="200",description="????json",content=@Content(mediaType="application/json",schema=@Schema(type="string",format="json",description="",implementation=Jack.class)))) public void save(){
  Tag tag=Tag.create(params());
  if (tag.save()) {
    render(200,"??",ViewType.string);
  }
  render(400,"??",ViewType.string);
}
