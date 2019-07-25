/** 
 * ????
 * @author wyq
 */
@Permissions("crm:customer:transfer") @NotNullValidate(value="customerIds",message="??id????") @NotNullValidate(value="newOwnerUserId",message="????????") @NotNullValidate(value="transferType",message="????????") @Before(Tx.class) public void transfer(@Para("") CrmCustomer crmCustomer){
  String[] customerIdsArr=crmCustomer.getCustomerIds().split(",");
  for (  String customerId : customerIdsArr) {
    crmCustomer.setCustomerId(Integer.valueOf(customerId));
    renderJson(crmCustomerService.updateOwnerUserId(crmCustomer));
    String changeType=crmCustomer.getChangeType();
    if (StrUtil.isNotEmpty(changeType)) {
      String[] changeTypeArr=changeType.split(",");
      for (      String type : changeTypeArr) {
        if ("1".equals(type)) {
          renderJson(crmContactsService.updateOwnerUserId(crmCustomer.getCustomerId(),crmCustomer.getNewOwnerUserId()) ? R.ok() : R.error());
        }
        if ("2".equals(type)) {
          renderJson(crmBusinessService.updateOwnerUserId(crmCustomer));
        }
        if ("3".equals(type)) {
          renderJson(crmContractService.updateOwnerUserId(crmCustomer));
        }
      }
    }
  }
}
