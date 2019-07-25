/** 
 * Decompose a linestring given as array of coordinates at a vertical line.
 * @param dateline x-axis intercept of the vertical line
 * @param coordinates coordinates forming the linestring
 * @return array of linestrings given as coordinate arrays
 */
private static Coordinate[][] decompose(double dateline,Coordinate[] coordinates){
  int offset=0;
  ArrayList<Coordinate[]> parts=new ArrayList<>();
  double shift=coordinates[0].x > DATELINE ? DATELINE : (coordinates[0].x < -DATELINE ? -DATELINE : 0);
  for (int i=1; i < coordinates.length; i++) {
    double t=intersection(coordinates[i - 1],coordinates[i],dateline);
    if (!Double.isNaN(t)) {
      Coordinate[] part;
      if (t < 1) {
        part=Arrays.copyOfRange(coordinates,offset,i + 1);
        part[part.length - 1]=Edge.position(coordinates[i - 1],coordinates[i],t);
        coordinates[offset + i - 1]=Edge.position(coordinates[i - 1],coordinates[i],t);
        shift(shift,part);
        offset=i - 1;
        shift=coordinates[i].x > DATELINE ? DATELINE : (coordinates[i].x < -DATELINE ? -DATELINE : 0);
      }
 else {
        part=shift(shift,Arrays.copyOfRange(coordinates,offset,i + 1));
        offset=i;
      }
      parts.add(part);
    }
  }
  if (offset == 0) {
    parts.add(shift(shift,coordinates));
  }
 else   if (offset < coordinates.length - 1) {
    Coordinate[] part=Arrays.copyOfRange(coordinates,offset,coordinates.length);
    parts.add(shift(shift,part));
  }
  return parts.toArray(new Coordinate[parts.size()][]);
}
