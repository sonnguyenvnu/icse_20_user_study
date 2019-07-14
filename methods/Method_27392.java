@Override public void onResourceReady(GlideDrawable resource,GlideAnimation<? super GlideDrawable> glideAnimation){
  if (container != null && container.get() != null) {
    TextView textView=container.get();
    textView.post(() -> {
      float width;
      float height;
      if (resource.getIntrinsicWidth() >= this.width) {
        float downScale=(float)resource.getIntrinsicWidth() / this.width;
        width=(float)(resource.getIntrinsicWidth() / downScale / 1.3);
        height=(float)(resource.getIntrinsicHeight() / downScale / 1.3);
      }
 else {
        float multiplier=(float)this.width / resource.getIntrinsicWidth();
        width=(float)resource.getIntrinsicWidth() * multiplier;
        height=(float)resource.getIntrinsicHeight() * multiplier;
      }
      Rect rect=new Rect(0,0,Math.round(width),Math.round(height));
      resource.setBounds(rect);
      urlDrawable.setBounds(rect);
      urlDrawable.setDrawable(resource);
      if (resource.isAnimated()) {
        urlDrawable.setCallback((Drawable.Callback)textView.getTag(R.id.drawable_callback));
        resource.setLoopCount(GlideDrawable.LOOP_FOREVER);
        resource.start();
      }
      textView.setText(textView.getText());
      textView.invalidate();
    }
);
  }
}
