private void sendSimple(){
  ApiRequest<Broadcast> request=ApiService.getInstance().sendBroadcast(mText,null,mLinkTitle,mLinkUrl);
  request.enqueue(new ApiRequest.Callback<Broadcast>(){
    @Override public void onResponse(    Broadcast response){
      onSuccessSimple(response);
    }
    @Override public void onErrorResponse(    ApiError error){
      onErrorSimple(error);
    }
  }
);
  mRequest=request;
}
