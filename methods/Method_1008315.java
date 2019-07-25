/** 
 * Create a connected list of a list of coordinates
 * @param points array of point
 * @param offset index of the first point
 * @param length number of points
 * @return Array of edges
 */
private static Edge[] ring(int component,boolean direction,boolean handedness,Coordinate[] points,int offset,Edge[] edges,int toffset,int length,final AtomicBoolean translated){
  final int top=top(points,offset,length);
  final int prev=(offset + ((top + length - 1) % length));
  final int next=(offset + ((top + 1) % length));
  boolean orientation=points[offset + prev].x > points[offset + next].x;
  double[] range=range(points,offset,length);
  final double rng=range[1] - range[0];
  boolean incorrectOrientation=component == 0 && handedness != orientation;
  if ((incorrectOrientation && (rng > DATELINE && rng != 2 * DATELINE)) || (translated.get() && component != 0)) {
    translate(points);
    if (component == 0) {
      translated.set(true);
    }
    if (component == 0 || (component != 0 && handedness == orientation)) {
      orientation=!orientation;
    }
  }
  return concat(component,direction ^ orientation,points,offset,edges,toffset,length);
}
