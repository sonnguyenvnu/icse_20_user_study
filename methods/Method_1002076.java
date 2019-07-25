/** 
 * ????
 * @author fengshuonan
 * @Date 2018/12/23 4:57 PM
 */
@RequestMapping(value="/detail/{deptId}") @Permission @ResponseBody public Object detail(@PathVariable("deptId") Long deptId){
  Dept dept=deptService.getById(deptId);
  DeptDto deptDto=new DeptDto();
  BeanUtil.copyProperties(dept,deptDto);
  deptDto.setPName(ConstantFactory.me().getDeptName(deptDto.getPid()));
  return deptDto;
}
