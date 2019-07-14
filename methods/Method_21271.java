private void init(final @NonNull Context context){
  if (!isInEditMode()) {
    this.backgroundPaint=new Paint();
    this.backgroundPaint.setStyle(Paint.Style.FILL);
    this.backgroundPaint.setColor(ContextCompat.getColor(context,R.color.ksr_cobalt_500));
    this.webEndpoint=environment().webEndpoint();
  }
}
