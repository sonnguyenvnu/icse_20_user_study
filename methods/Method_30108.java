@NonNull @Override public View onCreateView(@NonNull ViewGroup container,int position){
  final View layout=ViewUtils.inflate(R.layout.gallery_item,container);
  final ViewHolder holder=new ViewHolder(layout);
  layout.setTag(holder);
  holder.image.setOnPhotoTapListener((view,x,y) -> {
    if (mListener != null) {
      mListener.onTap();
    }
  }
);
  holder.largeImage.setOnClickListener(view -> {
    if (mListener != null) {
      mListener.onTap();
    }
  }
);
  loadImageForPosition(position,holder);
  container.addView(layout);
  return layout;
}
