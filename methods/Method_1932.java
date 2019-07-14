@Override public GlideHolder onCreateViewHolder(ViewGroup parent,int viewType){
  final InstrumentedImageView instrumentedImageView=new InstrumentedImageView(getContext());
  return new GlideHolder(getContext(),parent,instrumentedImageView,getPerfListener());
}
