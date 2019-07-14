/** 
 * Creates an immutable string from a C string.
 * @param allocator the allocator to use to allocate memory for the new object. Pass {@code NULL} or {@code kCFAllocatorDefault} to use the current default allocator.
 * @param cStr      the {@code NULL}-terminated C string to be used to create the  {@code CFString} object. The string must use an 8-bit encoding.
 * @param encoding  the encoding of the characters in the C string. The encoding must specify an 8-bit encoding. One of:<br><table><tr><td>{@link #kCFStringEncodingMacRoman}</td><td> {@link #kCFStringEncodingWindowsLatin1}</td><td> {@link #kCFStringEncodingISOLatin1}</td></tr><tr><td> {@link #kCFStringEncodingNextStepLatin}</td><td> {@link #kCFStringEncodingASCII}</td><td> {@link #kCFStringEncodingUnicode}</td></tr><tr><td> {@link #kCFStringEncodingUTF8}</td><td> {@link #kCFStringEncodingNonLossyASCII}</td><td> {@link #kCFStringEncodingUTF16}</td></tr><tr><td> {@link #kCFStringEncodingUTF16BE}</td><td> {@link #kCFStringEncodingUTF16LE}</td><td> {@link #kCFStringEncodingUTF32}</td></tr><tr><td> {@link #kCFStringEncodingUTF32BE}</td><td> {@link #kCFStringEncodingUTF32LE}</td></tr></table>
 */
@NativeType("CFStringRef") public static long CFStringCreateWithCString(@NativeType("CFAllocatorRef") long allocator,@NativeType("char const *") ByteBuffer cStr,@NativeType("CFStringEncoding") int encoding){
  return nCFStringCreateWithCString(allocator,memAddress(cStr),encoding);
}
