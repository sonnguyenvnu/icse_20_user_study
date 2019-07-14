/** 
 * Formats byte size to human readable bytecount. https://stackoverflow.com/questions/3758606/how-to-convert-byte-size-into-human-readable-format-in-java/3758880#3758880
 */
public static String humanReadableByteCount(final long bytes,final boolean useSi){
  final int unit=useSi ? 1000 : 1024;
  if (bytes < unit)   return bytes + " B";
  final int exp=(int)(Math.log(bytes) / Math.log(unit));
  final String pre=(useSi ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (useSi ? "" : "i");
  return String.format("%.1f %sB",bytes / Math.pow(unit,exp),pre);
}
