/** 
 * ???????
 * @author zxy
 */
public void win(@Para("deptId") Integer deptId,@Para("userId") Long userId,@Para("type") String type,@Para("startTime") String startTime,@Para("endTime") String endTime){
  renderJson(service.win(deptId,userId,type,startTime,endTime));
}
