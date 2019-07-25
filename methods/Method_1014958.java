/** 
 * @author wyq????
 */
@Permissions("crm:customer:lock") @NotNullValidate(value="ids",message="??id????") @NotNullValidate(value="isLock",message="????????") public void lock(@Para("") CrmCustomer crmCustomer){
  renderJson(crmCustomerService.lock(crmCustomer));
}
