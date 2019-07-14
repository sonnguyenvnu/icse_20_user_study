/** 
 * <p>Encodes up to three bytes of the array <var>source</var> and writes the resulting four Base64 bytes to <var>destination</var>. The source and destination arrays can be manipulated anywhere along their length by specifying <var>srcOffset</var> and <var>destOffset</var>. This method does not check to make sure your arrays are large enough to accomodate <var>srcOffset</var> + 3 for the <var>source</var> array or <var>destOffset</var> + 4 for the <var>destination</var> array. The actual number of significant bytes in your array is given by <var>numSigBytes</var>.</p> <p>This is the lowest level of the encoding methods with all possible parameters.</p>
 * @param source the array to convert
 * @param srcOffset the index where conversion begins
 * @param numSigBytes the number of significant bytes in your array
 * @param destination the array to hold the conversion
 * @param destOffset the index where output will be put
 * @return the <var>destination</var> array
 * @since 1.3
 */
private static byte[] encode3to4(byte[] source,int srcOffset,int numSigBytes,byte[] destination,int destOffset,int options){
  byte[] ALPHABET=getAlphabet(options);
  int inBuff=(numSigBytes > 0 ? ((source[srcOffset] << 24) >>> 8) : 0) | (numSigBytes > 1 ? ((source[srcOffset + 1] << 24) >>> 16) : 0) | (numSigBytes > 2 ? ((source[srcOffset + 2] << 24) >>> 24) : 0);
switch (numSigBytes) {
case 3:
    destination[destOffset]=ALPHABET[(inBuff >>> 18)];
  destination[destOffset + 1]=ALPHABET[(inBuff >>> 12) & 0x3f];
destination[destOffset + 2]=ALPHABET[(inBuff >>> 6) & 0x3f];
destination[destOffset + 3]=ALPHABET[(inBuff) & 0x3f];
return destination;
case 2:
destination[destOffset]=ALPHABET[(inBuff >>> 18)];
destination[destOffset + 1]=ALPHABET[(inBuff >>> 12) & 0x3f];
destination[destOffset + 2]=ALPHABET[(inBuff >>> 6) & 0x3f];
destination[destOffset + 3]=EQUALS_SIGN;
return destination;
case 1:
destination[destOffset]=ALPHABET[(inBuff >>> 18)];
destination[destOffset + 1]=ALPHABET[(inBuff >>> 12) & 0x3f];
destination[destOffset + 2]=EQUALS_SIGN;
destination[destOffset + 3]=EQUALS_SIGN;
return destination;
default :
return destination;
}
}
