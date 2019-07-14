private void updateText(){
  String text=getString(R.string.drawee_span_simple_text);
  int imagePosition=text.indexOf('#');
  DraweeSpanStringBuilder draweeSpanStringBuilder=new DraweeSpanStringBuilder(text);
  DraweeHierarchy draweeHierarchy=GenericDraweeHierarchyBuilder.newInstance(getResources()).setPlaceholderImage(new ColorDrawable(Color.RED)).setActualImageScaleType(mScaleType).build();
  DraweeController controller=Fresco.newDraweeControllerBuilder().setUri(mInlineImageUri).build();
  draweeSpanStringBuilder.setImageSpan(getContext(),draweeHierarchy,controller,imagePosition,200,200,false,DraweeSpan.ALIGN_CENTER);
  int imagePosition2=text.indexOf('@');
  DraweeHierarchy draweeAnimatedHierarchy=GenericDraweeHierarchyBuilder.newInstance(getResources()).setPlaceholderImage(new ColorDrawable(Color.RED)).setActualImageScaleType(mScaleType).build();
  DraweeController animatedController=Fresco.newDraweeControllerBuilder().setUri(mInlineAnimatedImageUri).setAutoPlayAnimations(true).build();
  draweeSpanStringBuilder.setImageSpan(getContext(),draweeAnimatedHierarchy,animatedController,imagePosition2,200,200,false,DraweeSpan.ALIGN_CENTER);
  mDraweeSpanTextView.setDraweeSpanStringBuilder(draweeSpanStringBuilder);
}
