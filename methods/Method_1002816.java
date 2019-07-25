@Override public HttpResponse serve(ServiceRequestContext ctx,HttpRequest req) throws Exception {
  final HttpEncodingType encodingType=HttpEncoders.getWrapperForRequest(req);
  final HttpResponse delegateResponse=delegate().serve(ctx,req);
  if (encodingType == null || !encodableRequestHeadersPredicate.test(req.headers())) {
    return delegateResponse;
  }
  return new HttpEncodedResponse(delegateResponse,encodingType,encodableContentTypePredicate,minBytesToForceChunkedAndEncoding);
}
