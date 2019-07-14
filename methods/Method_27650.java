@Override public void onUpdatePinIcon(@NonNull Gist gist){
  pinUnpin.setImageDrawable(PinnedGists.isPinned(gist.getGistId().hashCode()) ? ContextCompat.getDrawable(this,R.drawable.ic_pin_filled) : ContextCompat.getDrawable(this,R.drawable.ic_pin));
}
