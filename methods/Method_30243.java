protected void bindPhotoListHolder(RecyclerView.ViewHolder holder,T item,List<Photo> photoList,boolean excludeFirstPhoto){
  PhotoListHolder photoListHolder=(PhotoListHolder)holder;
  List<Photo> originalPhotoList=photoList;
  if (excludeFirstPhoto) {
    photoList=photoList.subList(1,photoList.size());
  }
  ViewUtils.setVisibleOrGone(photoListHolder.photoList,!photoList.isEmpty());
  HorizontalImageAdapter adapter=(HorizontalImageAdapter)photoListHolder.photoList.getAdapter();
  adapter.replace(photoList);
  Context context=RecyclerViewUtils.getContext(holder);
  adapter.setOnItemClickListener((parent,itemView,item_,photoPosition) -> {
    if (excludeFirstPhoto) {
      ++photoPosition;
    }
    context.startActivity(GalleryActivity.makeImageListIntent(originalPhotoList,photoPosition,context));
  }
);
  photoListHolder.viewMoreButton.setOnClickListener(view -> {
    UriHandler.open(item.url + "photos",context);
  }
);
}
