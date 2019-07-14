public RequestListener getRequestListenerForRequest(ImageRequest imageRequest,@Nullable RequestListener requestListener){
  if (requestListener == null) {
    if (imageRequest.getRequestListener() == null) {
      return mRequestListener;
    }
    return new ForwardingRequestListener(mRequestListener,imageRequest.getRequestListener());
  }
 else {
    if (imageRequest.getRequestListener() == null) {
      return new ForwardingRequestListener(mRequestListener,requestListener);
    }
    return new ForwardingRequestListener(mRequestListener,requestListener,imageRequest.getRequestListener());
  }
}
