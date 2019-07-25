/** 
 * Fetches the Shape with the given ID in the given type and index.
 * @param getRequest GetRequest containing index, type and id
 * @param path Name or path of the field in the Shape Document where the Shape itself is located
 */
private void fetch(Client client,GetRequest getRequest,String path,ActionListener<ShapeBuilder> listener){
  if (ShapesAvailability.JTS_AVAILABLE == false) {
    throw new IllegalStateException("JTS not available");
  }
  getRequest.preference("_local");
  client.get(getRequest,new ActionListener<GetResponse>(){
    @Override public void onResponse(    GetResponse response){
      try {
        if (!response.isExists()) {
          throw new IllegalArgumentException("Shape with ID [" + getRequest.id() + "] in type [" + getRequest.type() + "] not found");
        }
        if (response.isSourceEmpty()) {
          throw new IllegalArgumentException("Shape with ID [" + getRequest.id() + "] in type [" + getRequest.type() + "] source disabled");
        }
        String[] pathElements=path.split("\\.");
        int currentPathSlot=0;
        try (XContentParser parser=XContentHelper.createParser(NamedXContentRegistry.EMPTY,response.getSourceAsBytesRef())){
          XContentParser.Token currentToken;
          while ((currentToken=parser.nextToken()) != XContentParser.Token.END_OBJECT) {
            if (currentToken == XContentParser.Token.FIELD_NAME) {
              if (pathElements[currentPathSlot].equals(parser.currentName())) {
                parser.nextToken();
                if (++currentPathSlot == pathElements.length) {
                  listener.onResponse(ShapeParser.parse(parser));
                }
              }
 else {
                parser.nextToken();
                parser.skipChildren();
              }
            }
          }
          throw new IllegalStateException("Shape with name [" + getRequest.id() + "] found but missing " + path + " field");
        }
       }
 catch (      Exception e) {
        onFailure(e);
      }
    }
    @Override public void onFailure(    Exception e){
      listener.onFailure(e);
    }
  }
);
}
