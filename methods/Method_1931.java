@Override public AQueryHolder onCreateViewHolder(ViewGroup parent,int viewType){
  final InstrumentedImageView instrImageView=new InstrumentedImageView(getContext());
  return new AQueryHolder(getContext(),mAQuery,parent,instrImageView,getPerfListener());
}
