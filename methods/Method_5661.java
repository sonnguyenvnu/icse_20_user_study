/** 
 * Calls  {@link #getPeriodPosition(Window,Period,int,long,long)} with a zero default positionprojection.
 */
public final Pair<Object,Long> getPeriodPosition(Window window,Period period,int windowIndex,long windowPositionUs){
  return Assertions.checkNotNull(getPeriodPosition(window,period,windowIndex,windowPositionUs,0));
}
