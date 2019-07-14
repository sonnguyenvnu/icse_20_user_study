private @NonNull View inflateView(final @NonNull ViewGroup viewGroup,final @LayoutRes int viewType){
  final LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
  return layoutInflater.inflate(viewType,viewGroup,false);
}
