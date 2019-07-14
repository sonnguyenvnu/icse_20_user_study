public static void tintImageViewIcon(ImageView imageView){
  imageView.setImageDrawable(tintIcon(imageView.getDrawable(),imageView.getContext()));
}
