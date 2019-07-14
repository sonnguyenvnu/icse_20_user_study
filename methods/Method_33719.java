@Override public void displayImage(Context context,Object url,ImageView imageView){
  Glide.with(context).load(url).placeholder(R.drawable.shape_bg_loading).error(R.drawable.shape_bg_loading).transition(DrawableTransitionOptions.withCrossFade(1000)).into(imageView);
}
