/** 
 * The total request time from start to finish, minus added delay
 */
public int getServeTime(){
  return processTime + responseSendTime;
}
