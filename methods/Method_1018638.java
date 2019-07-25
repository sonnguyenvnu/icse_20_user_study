/** 
 * @return original delegate {@link LuminanceSource} since invert undoes itself
 */
@Override public LuminanceSource invert(){
  return delegate;
}
