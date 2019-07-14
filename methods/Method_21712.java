@Override public String explain(){
  try {
    XContentBuilder firstBuilder=XContentFactory.contentBuilder(XContentType.JSON).prettyPrint();
    this.firstSearchRequest.request().source().toXContent(firstBuilder,ToXContent.EMPTY_PARAMS);
    XContentBuilder secondBuilder=XContentFactory.contentBuilder(XContentType.JSON).prettyPrint();
    this.secondSearchRequest.request().source().toXContent(secondBuilder,ToXContent.EMPTY_PARAMS);
    String explained=String.format("performing %s on :\n left query:\n%s\n right query:\n%s",this.relation.name,BytesReference.bytes(firstBuilder).utf8ToString(),BytesReference.bytes(secondBuilder).utf8ToString());
    return explained;
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return null;
}
