/** 
 * @author wyq??
 */
@Before(Tx.class) public void add(@Para("") AdminRole adminRole){
  renderJson(adminRoleService.save(adminRole));
}
