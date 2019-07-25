/** 
 * NOTE: this iterator will be called very frequently, avoid complicated code.
 * @return
 */
@Override public Iterator<IGeoPoint> iterator(){
  return mPoints.iterator();
}
