/** 
 * Assigns sort modes A value from 0 to 2 defines sort mode as name/last modified/size in ascending order Values from 3 to 5 defines sort mode as name/last modified/size in descending order <p> Final value of  {@link #sortby} varies from 0 to 2
 */
public void getSortModes(){
  int t=Integer.parseInt(Sp.getString("sortbyApps","0"));
  if (t <= 2) {
    sortby=t;
    asc=1;
  }
 else   if (t > 2) {
    asc=-1;
    sortby=t - 3;
  }
}
