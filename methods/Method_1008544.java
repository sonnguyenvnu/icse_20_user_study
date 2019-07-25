private void fetch(TermsLookup termsLookup,Client client,ActionListener<List<Object>> actionListener){
  GetRequest getRequest=new GetRequest(termsLookup.index(),termsLookup.type(),termsLookup.id()).preference("_local").routing(termsLookup.routing());
  client.get(getRequest,new ActionListener<GetResponse>(){
    @Override public void onResponse(    GetResponse getResponse){
      List<Object> terms=new ArrayList<>();
      if (getResponse.isSourceEmpty() == false) {
        List<Object> extractedValues=XContentMapValues.extractRawValues(termsLookup.path(),getResponse.getSourceAsMap());
        terms.addAll(extractedValues);
      }
      actionListener.onResponse(terms);
    }
    @Override public void onFailure(    Exception e){
      actionListener.onFailure(e);
    }
  }
);
}
