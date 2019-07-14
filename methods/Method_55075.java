/** 
 * Unsafe version of  {@link #class$}. 
 */
public static int nclass$(long struct){
  return UNSAFE.getInt(null,struct + Visual.CLASS);
}
