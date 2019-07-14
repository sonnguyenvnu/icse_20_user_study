private void initData(){
  RxQRCode.createQRCode("???:" + System.currentTimeMillis(),800,800,mIvCode);
  mIvLinecode.setImageBitmap(RxBarCode.createBarCode("" + System.currentTimeMillis(),1000,300));
}
