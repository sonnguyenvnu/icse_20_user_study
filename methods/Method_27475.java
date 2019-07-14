public static ReposViewHolder newInstance(ViewGroup viewGroup,BaseRecyclerAdapter adapter,boolean isStarred,boolean withImage){
  if (withImage) {
    return new ReposViewHolder(getView(viewGroup,R.layout.repos_row_item),adapter,isStarred,true);
  }
 else {
    return new ReposViewHolder(getView(viewGroup,R.layout.repos_row_no_image_item),adapter,isStarred,false);
  }
}
