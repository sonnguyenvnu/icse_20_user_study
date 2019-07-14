@Nullable public synchronized RequestListener getRequestListener(){
  RequestListener imageOriginRequestListener=null;
  if (mImageOriginListener != null) {
    imageOriginRequestListener=new ImageOriginRequestListener(getId(),mImageOriginListener);
  }
  if (mRequestListeners != null) {
    ForwardingRequestListener requestListener=new ForwardingRequestListener(mRequestListeners);
    if (imageOriginRequestListener != null) {
      requestListener.addRequestListener(imageOriginRequestListener);
    }
    return requestListener;
  }
  return imageOriginRequestListener;
}
