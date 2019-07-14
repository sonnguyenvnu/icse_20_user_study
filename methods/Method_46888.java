@Override protected void onCancelled(){
  super.onCancelled();
  dbViewerFragment.getActivity().onBackPressed();
}
