/** 
 * ????
 * @author fengshuonan
 * @Date 2018/12/23 4:57 PM
 */
@BussinessLog(value="????",key="simpleName",dict=DeptDict.class) @RequestMapping(value="/update") @Permission @ResponseBody public ResponseData update(Dept dept){
  deptService.editDept(dept);
  return SUCCESS_TIP;
}
