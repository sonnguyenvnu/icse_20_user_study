/** 
 * Sets a  {@link MediaFormat} float value. Does nothing if {@code value} is {@link Format#NO_VALUE}.
 * @param format The {@link MediaFormat} being configured.
 * @param key The key to set.
 * @param value The value to set.
 */
public static void maybeSetFloat(MediaFormat format,String key,float value){
  if (value != Format.NO_VALUE) {
    format.setFloat(key,value);
  }
}
