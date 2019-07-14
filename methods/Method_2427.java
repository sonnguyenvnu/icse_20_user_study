/** 
 * ??????????
 * @param point
 * @return
 */
public boolean overlapsWith(int point){
  return this.start <= point && point <= this.end;
}
