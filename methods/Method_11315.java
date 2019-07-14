private int efficientTextSizeSearch(final int start,final int end,final SizeTester sizeTester,final RectF availableSpace){
  if (!enableSizeCache) {
    return binarySearch(start,end,sizeTester,availableSpace);
  }
  final String text=getText().toString();
  final int key=text == null ? 0 : text.length();
  int size=textCachedSizes.get(key);
  if (size != 0) {
    return size;
  }
  size=binarySearch(start,end,sizeTester,availableSpace);
  textCachedSizes.put(key,size);
  return size;
}
