private void storeEntries(List<CountingMemoryCacheInspector.DumpInfoEntry<CacheKey,CloseableImage>> entries,int i,PrintStream writer,File directory) throws IOException {
  String filename;
  for (  CountingMemoryCacheInspector.DumpInfoEntry<CacheKey,CloseableImage> entry : entries) {
    CloseableImage closeableImage=entry.value.get();
    if (closeableImage instanceof CloseableBitmap) {
      CloseableBitmap closeableBitmap=(CloseableBitmap)closeableImage;
      filename="tmp" + i + ".png";
      writer.println(formatStrLocaleSafe("Storing image %d as %s. Key: %s",i,filename,entry.key));
      storeImage(closeableBitmap.getUnderlyingBitmap(),new File(directory,filename),Bitmap.CompressFormat.PNG,100);
    }
 else {
      writer.println(formatStrLocaleSafe("Image %d has unrecognized type %s. Key: %s",i,closeableImage,entry.key));
    }
    i++;
  }
}
