public ExampleRequestResponse options(){
  OptionsRequest request=new OptionsRequestBuilder(_uriTemplate,_requestOptions).build();
  return buildRequestResponse(request,_resourceSchema,buildResourceMethodDescriptorForRestMethod(request));
}
