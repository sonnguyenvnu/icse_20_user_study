@NonNull protected static <T extends BaseMediaFragment>T newInstance(@NonNull T mediaFragment,@NonNull Media media){
  Bundle args=new Bundle();
  args.putParcelable(ARGS_MEDIA,media);
  mediaFragment.setArguments(args);
  return mediaFragment;
}
