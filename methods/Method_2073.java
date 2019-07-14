public static Intent getStartIntent(Context context,Uri imageUri){
  Intent intent=new Intent(context,ImageDetailsActivity.class);
  intent.setData(imageUri);
  return intent;
}
