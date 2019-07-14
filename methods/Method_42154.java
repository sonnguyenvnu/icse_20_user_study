private void fetchArgs(){
  Bundle args=getArguments();
  if (args == null)   throw new RuntimeException("Must pass arguments to Media Fragments!");
  media=getArguments().getParcelable(ARGS_MEDIA);
}
