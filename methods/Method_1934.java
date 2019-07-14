@Override public UilHolder onCreateViewHolder(ViewGroup parent,int viewType){
  final InstrumentedImageView instrumentedImageView=new InstrumentedImageView(getContext());
  return new UilHolder(getContext(),mImageLoader,parent,instrumentedImageView,getPerfListener());
}
