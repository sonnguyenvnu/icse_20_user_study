/** 
 * Returns a copy of this  {@link DataSpec} with the specified Uri.
 * @param uri The new source {@link Uri}.
 * @return The copied {@link DataSpec} with the specified Uri.
 */
public DataSpec withUri(Uri uri){
  return new DataSpec(uri,httpMethod,httpBody,absoluteStreamPosition,position,length,key,flags);
}
