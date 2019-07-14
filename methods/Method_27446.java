public static GistFilesViewHolder newInstance(@NonNull ViewGroup parent,GistFilesAdapter adapter,boolean isOwner){
  return new GistFilesViewHolder(getView(parent,R.layout.gist_files_row_item),adapter,isOwner);
}
