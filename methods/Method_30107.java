private static Intent makeIntent(ArrayList<Uri> imageList,int position,Context context){
  return new Intent(context,GalleryActivity.class).putParcelableArrayListExtra(EXTRA_IMAGE_LIST,imageList).putExtra(EXTRA_POSITION,position);
}
