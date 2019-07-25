public static byte size(long hd,long hr){
  if (hd == 0 && hr == 0)   return 1;
  byte num_bytes_for_hd=bytesRequiredFor(hd), num_bytes_for_delta=bytesRequiredFor(hr - hd);
  return (byte)(num_bytes_for_hd + num_bytes_for_delta + 1);
}
