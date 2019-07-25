/** 
 * @author wyq????
 */
@Permissions("crm:contract:transfer") @NotNullValidate(value="contractIds",message="??id????") @NotNullValidate(value="newOwnerUserId",message="???id????") @NotNullValidate(value="transferType",message="????????") public void transfer(@Para("") CrmContract crmContract){
  renderJson(crmContractService.transfer(crmContract));
}
