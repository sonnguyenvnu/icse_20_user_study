@Override public View getView(BigImageView parent){
  mProgressPieView=(ProgressPieView)LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_progress_pie_indicator,parent,false);
  return mProgressPieView;
}
