@Override public Fragment getItem(int pos){
  Media media=this.media.get(pos);
  if (media.isVideo())   return VideoFragment.newInstance(media);
  if (media.isGif())   return GifFragment.newInstance(media);
 else   return ImageFragment.newInstance(media);
}
