@Override public void onViewCreated(View view,@Nullable Bundle savedInstanceState){
  SimpleDraweeView simpleDraweeView=(SimpleDraweeView)view.findViewById(R.id.drawee_view);
  ImageDecodeOptions imageDecodeOptionsWithCustomDecoder=new ImageDecodeOptionsBuilder().setCustomImageDecoder(CUSTOM_COLOR_DECODER).build();
  AbstractDraweeController controller=Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequestBuilder.newBuilderWithResourceId(R.raw.custom_color1).setImageDecodeOptions(imageDecodeOptionsWithCustomDecoder).build()).build();
  simpleDraweeView.setController(controller);
}
