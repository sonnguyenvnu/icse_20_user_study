@Override public void bind(@NonNull FilesListModel filesListModel){
  fileName.setText(filesListModel.getFilename());
  language.setText(SpannableBuilder.builder().bold(filesListModel.getType()));
  size.setText(Formatter.formatFileSize(size.getContext(),filesListModel.getSize()));
  delete.setVisibility(isOwner ? View.VISIBLE : View.GONE);
  edit.setVisibility(isOwner ? View.VISIBLE : View.GONE);
}
