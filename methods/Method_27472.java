public static RepoFilePathsViewHolder newInstance(ViewGroup viewGroup,BaseRecyclerAdapter adapter){
  return new RepoFilePathsViewHolder(getView(viewGroup,R.layout.file_path_row_item),adapter);
}
