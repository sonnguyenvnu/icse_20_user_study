/** 
 * The coordinates setup by the builder will be assembled to a polygon. The result will consist of a set of polygons. Each of these components holds a list of linestrings defining the polygon: the first set of coordinates will be used as the shell of the polygon. The others are defined to holes within the polygon. This Method also wraps the polygons at the dateline. In order to this fact the result may contains more polygons and less holes than defined in the builder it self.
 * @return coordinates of the polygon
 */
public Coordinate[][][] coordinates(){
  int numEdges=shell.coordinates.size() - 1;
  for (int i=0; i < holes.size(); i++) {
    numEdges+=holes.get(i).coordinates.size() - 1;
    validateHole(shell,this.holes.get(i));
  }
  Edge[] edges=new Edge[numEdges];
  Edge[] holeComponents=new Edge[holes.size()];
  final AtomicBoolean translated=new AtomicBoolean(false);
  int offset=createEdges(0,orientation,shell,null,edges,0,translated);
  for (int i=0; i < holes.size(); i++) {
    int length=createEdges(i + 1,orientation,shell,this.holes.get(i),edges,offset,translated);
    holeComponents[i]=edges[offset];
    offset+=length;
  }
  int numHoles=holeComponents.length;
  numHoles=merge(edges,0,intersections(+DATELINE,edges),holeComponents,numHoles);
  numHoles=merge(edges,0,intersections(-DATELINE,edges),holeComponents,numHoles);
  return compose(edges,holeComponents,numHoles);
}
