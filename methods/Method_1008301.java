private static Coordinate[] shift(double shift,Coordinate... coordinates){
  if (shift != 0) {
    for (int j=0; j < coordinates.length; j++) {
      coordinates[j]=new Coordinate(coordinates[j].x - 2 * shift,coordinates[j].y);
    }
  }
  return coordinates;
}
