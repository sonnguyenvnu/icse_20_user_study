/** 
 * ??????
 * @author zxy
 */
public void portrait(@Para("deptId") Integer deptId,@Para("userId") Long userId,@Para("type") String type,@Para("startTime") String startTime,@Para("endTime") String endTime){
  renderJson(service.portrait(deptId,userId,type,startTime,endTime));
}
