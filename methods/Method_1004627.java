/** 
 * Returns mercator Y corresponding to latitude. See http://en.wikipedia.org/wiki/Mercator_projection .
 */
static double mercator(double lat){
  return log(tan(lat * 0.5 + PI / 4));
}
