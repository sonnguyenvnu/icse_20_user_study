private void init(Context context,AttributeSet attrs){
  ShadowDrawable drawable=ShadowDrawable.fromAttributeSet(context,attrs);
  setLayerType(View.LAYER_TYPE_SOFTWARE,null);
  ViewCompat.setBackground(this,drawable);
}
