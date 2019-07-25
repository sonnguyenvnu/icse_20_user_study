public String digest(){
  final String status=status();
  if (status == null) {
    return null;
  }
  if (status.startsWith(STATUS_DIGEST_PREFIX_16)) {
    return status.substring(STATUS_DIGEST_PREFIX_16.length()).trim();
  }
  final int digestIndex=status.indexOf(STATUS_DIGEST_PREFIX_18);
  final int sizeIndex=status.indexOf(STATUS_SIZE_PREFIX_18);
  if (digestIndex > -1 && sizeIndex > digestIndex) {
    final int start=digestIndex + STATUS_DIGEST_PREFIX_18.length();
    return status.substring(start,sizeIndex - 1);
  }
  return null;
}
