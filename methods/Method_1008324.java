/** 
 * Calculate the geohash of a neighbor of a geohash
 * @param geohash the geohash of a cell
 * @param level   level of the geohash
 * @param dx      delta of the first grid coordinate (must be -1, 0 or +1)
 * @param dy      delta of the second grid coordinate (must be -1, 0 or +1)
 * @return geohash of the defined cell
 */
public static final String neighbor(String geohash,int level,int dx,int dy){
  int cell=BASE_32_STRING.indexOf(geohash.charAt(level - 1));
  int x0=cell & 1;
  int y0=cell & 2;
  int x1=cell & 4;
  int y1=cell & 8;
  int x2=cell & 16;
  int x=x0 + (x1 / 2) + (x2 / 4);
  int y=(y0 / 2) + (y1 / 4);
  if (level == 1) {
    if ((dy < 0 && y == 0) || (dy > 0 && y == 3)) {
      return null;
    }
 else {
      return Character.toString(encode(x + dx,y + dy));
    }
  }
 else {
    final int nx=((level % 2) == 1) ? (x + dx) : (x + dy);
    final int ny=((level % 2) == 1) ? (y + dy) : (y + dx);
    if (nx >= 0 && nx <= 7 && ny >= 0 && ny <= 3) {
      return geohash.substring(0,level - 1) + encode(nx,ny);
    }
 else {
      String neighbor=neighbor(geohash,level - 1,dx,dy);
      return (neighbor != null) ? neighbor + encode(nx,ny) : neighbor;
    }
  }
}
