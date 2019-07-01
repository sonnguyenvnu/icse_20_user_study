/** 
 * Builds an inexact opaque palette of at most  {@code max} colors in {@code src}using a variation of the Median Cut algorithm. Accurate to 6 bits per component, and works by splitting the color bounding box most heavily populated by colors along the component which splits the colors in that box most evenly.
 * @param src the image whose palette to build
 * @param max the maximum number of colors the palette can contain
 * @return the palette of at most {@code max} colors
 */
public Palette _XXXXX_(final BufferedImage src,final int max){
  final int precision=6;
  final int tableScale=precision * COMPONENTS;
  final int tableSize=1 << tableScale;
  final int[] table=new int[tableSize];
  final int width=src.getWidth();
  final int height=src.getHeight();
  List<ColorSpaceSubset> subsets=new ArrayList<>();
  final ColorSpaceSubset all=new ColorSpaceSubset(width * height,precision);
  subsets.add(all);
  if (LOGGER.isLoggable(Level.FINEST)) {
    final int preTotal=getFrequencyTotal(table,all.mins,all.maxs,precision);
    LOGGER.finest("pre total: " + preTotal);
  }
  for (int y=0; y < height; y++) {
    for (int x=0; x < width; x++) {
      final int argb=src.getRGB(x,y);
      final int index=pixelToQuantizationTableIndex(argb,precision);
      table[index]++;
    }
  }
  if (LOGGER.isLoggable(Level.FINEST)) {
    final int allTotal=getFrequencyTotal(table,all.mins,all.maxs,precision);
    LOGGER.finest("all total: " + allTotal);
    LOGGER.finest("width * height: " + (width * height));
  }
  subsets=divide(subsets,max,table,precision);
  if (LOGGER.isLoggable(Level.FINEST)) {
    LOGGER.finest("subsets: " + subsets.size());
    LOGGER.finest("width*height: " + width * height);
  }
  for (int i=0; i < subsets.size(); i++) {
    final ColorSpaceSubset subset=subsets.get(i);
    subset.setAverageRGB(table);
    if (LOGGER.isLoggable(Level.FINEST)) {
      subset.dump(i + ": ");
    }
  }
  Collections.sort(subsets,ColorSpaceSubset.RGB_COMPARATOR);
  return new QuantizedPalette(subsets,precision);
}