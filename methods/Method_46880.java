@Override void addElements(ArrayList<CompressedObjectParcelable> elements){
  TarArchiveInputStream tarInputStream=null;
  try {
    tarInputStream=new TarArchiveInputStream(new GzipCompressorInputStream(new FileInputStream(filePath)));
    TarArchiveEntry entry;
    while ((entry=tarInputStream.getNextTarEntry()) != null) {
      String name=entry.getName();
      if (!CompressedHelper.isEntryPathValid(name)) {
        AppConfig.toast(context.get(),context.get().getString(R.string.multiple_invalid_archive_entries));
        continue;
      }
      if (name.endsWith(SEPARATOR))       name=name.substring(0,name.length() - 1);
      boolean isInBaseDir=relativePath.equals("") && !name.contains(SEPARATOR);
      boolean isInRelativeDir=name.contains(SEPARATOR) && name.substring(0,name.lastIndexOf(SEPARATOR)).equals(relativePath);
      if (isInBaseDir || isInRelativeDir) {
        elements.add(new CompressedObjectParcelable(entry.getName(),entry.getLastModifiedDate().getTime(),entry.getSize(),entry.isDirectory()));
      }
    }
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
}
