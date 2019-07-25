private void message(final OverlayItem pItem){
  Toast.makeText(getActivity(),pItem.getTitle() + ": " + pItem.getSnippet(),Toast.LENGTH_LONG).show();
}
