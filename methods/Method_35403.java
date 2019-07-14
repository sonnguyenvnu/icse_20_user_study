@Override public void bind(Folder folder,int position){
  textViewName.setText(folder.getName());
  textViewInfo.setText(getContext().getString(R.string.mp_local_files_folder_list_item_info_formatter,folder.getNumOfSongs(),folder.getPath()));
}
