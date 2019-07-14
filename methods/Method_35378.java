@Override public void bind(FileWrapper fileWrapper,int position){
  final File file=fileWrapper.file;
  final boolean isItemSelected=fileWrapper.selected;
  if (file.isDirectory()) {
    setSelected(isItemSelected);
    imageViewFile.setImageResource(isItemSelected ? R.drawable.ic_folder_selected : R.drawable.ic_folder);
    File[] files=file.listFiles(SystemFileFilter.DEFAULT_INSTANCE);
    int itemCount=files == null ? 0 : files.length;
    textViewInfo.setText(getContext().getResources().getQuantityString(R.plurals.mp_directory_items_formatter,itemCount,itemCount));
  }
 else {
    setSelected(false);
    if (FileUtils.isMusic(file)) {
      imageViewFile.setImageResource(R.drawable.ic_file_music);
    }
 else     if (FileUtils.isLyric(file)) {
      imageViewFile.setImageResource(R.drawable.ic_file_lyric);
    }
 else {
      imageViewFile.setImageResource(R.drawable.ic_file);
    }
    textViewInfo.setText(FileUtils.readableFileSize(file.length()));
  }
  textViewName.setText(file.getName());
  Date lastModified=new Date(file.lastModified());
  textViewDate.setText(DATE_FORMATTER.format(lastModified));
}
