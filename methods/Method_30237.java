protected PhotoListHolder createPhotoListHolder(ViewGroup parent){
  PhotoListHolder holder=new PhotoListHolder(ViewUtils.inflate(R.layout.item_fragment_photo_list,parent));
  holder.photoList.setHasFixedSize(true);
  holder.photoList.setLayoutManager(new LinearLayoutManager(parent.getContext(),LinearLayoutManager.HORIZONTAL,false));
  holder.photoList.addItemDecoration(new DividerItemDecoration(DividerItemDecoration.HORIZONTAL,R.drawable.transparent_divider_vertical_4dp,holder.photoList.getContext()));
  holder.photoList.setAdapter(new HorizontalImageAdapter());
  return holder;
}
