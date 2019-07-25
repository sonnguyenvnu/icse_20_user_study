/** 
 * @since 6.0.2
 */
public void put(final int pZoom){
  final int max=1 << pZoom;
  put(pZoom,0,0,max - 1,max - 1);
}
