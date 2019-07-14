@Override public void bind(@NonNull RepoFile filesModel){
  contentTypeImage.setContentDescription(String.format("%s %s",filesModel.getName(),file));
  title.setText(filesModel.getName());
  if (filesModel.getType() != null && filesModel.getType().getIcon() != 0) {
    contentTypeImage.setImageResource(filesModel.getType().getIcon());
    if (filesModel.getType() == FilesType.file) {
      size.setText(Formatter.formatFileSize(size.getContext(),filesModel.getSize()));
      size.setVisibility(View.VISIBLE);
    }
 else {
      size.setVisibility(View.GONE);
    }
  }
}
