private void setImageView(){
  Picasso.with(getActivity()).load(imageUri).fit().centerCrop().into(imageView);
}
