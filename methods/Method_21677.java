@Override public String explain(){
  try {
    XContentBuilder firstBuilder=XContentFactory.contentBuilder(XContentType.JSON).prettyPrint();
    firstTable.getRequestBuilder().request().source().toXContent(firstBuilder,ToXContent.EMPTY_PARAMS);
    XContentBuilder secondBuilder=XContentFactory.contentBuilder(XContentType.JSON).prettyPrint();
    secondTable.getRequestBuilder().request().source().toXContent(secondBuilder,ToXContent.EMPTY_PARAMS);
    String explained=String.format(" first query:\n%s\n second query:\n%s",BytesReference.bytes(firstBuilder).utf8ToString(),BytesReference.bytes(secondBuilder).utf8ToString());
    return explained;
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return null;
}
