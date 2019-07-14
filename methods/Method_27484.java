public static TeamsViewHolder newInstance(@NonNull ViewGroup viewGroup,@NonNull BaseRecyclerAdapter adapter){
  return new TeamsViewHolder(getView(viewGroup,R.layout.feeds_row_no_image_item),adapter);
}
