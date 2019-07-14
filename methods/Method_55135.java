/** 
 * Creates a  {@code CFBundle} object.
 * @param allocator the allocator to use to allocate memory for the new object. Pass {@code NULL} or {@code kCFAllocatorDefault} to use the current default allocator.
 * @param bundleURL the location of the bundle for which to create a {@code CFBundle} object
 */
@NativeType("CFBundleRef") public static long CFBundleCreate(@NativeType("CFAllocatorRef") long allocator,@NativeType("CFURLRef") long bundleURL){
  if (CHECKS) {
    check(bundleURL);
  }
  return nCFBundleCreate(allocator,bundleURL);
}
