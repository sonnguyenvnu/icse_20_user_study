private Record transfer(String taskId){
  Record task=Db.findFirst("select * from 72crm_task where task_id = ?",taskId);
  task.set("stop_time",DateUtil.formatDate(task.getDate("stop_time")));
  task.set("mainUser",Db.findFirst("select user_id,realname,img from 72crm_admin_user where user_id = ?",task.getInt("main_user_id")));
  task.set("createUser",Db.findFirst("select user_id,realname,img from 72crm_admin_user where user_id = ?",task.getInt("create_user_id")));
  ArrayList<Record> labelList=new ArrayList<>();
  ArrayList<Record> ownerUserList=new ArrayList<>();
  if (StrUtil.isNotBlank(task.getStr("lable_id"))) {
    String[] lableIds=task.getStr("lable_id").split(",");
    for (    String lableId : lableIds) {
      if (StrUtil.isNotBlank(lableId)) {
        Record lable=Db.findFirst("select lable_id,name as lableName,color from 72crm_work_task_lable where lable_id = ?",lableId);
        labelList.add(lable);
      }
    }
  }
  if (StrUtil.isNotBlank(task.getStr("owner_user_id"))) {
    String[] ownerUserIds=task.getStr("owner_user_id").split(",");
    for (    String ownerUserId : ownerUserIds) {
      if (StrUtil.isNotBlank(ownerUserId)) {
        Record ownerUser=Db.findFirst("select user_id,realname,img from 72crm_admin_user where user_id = ?",ownerUserId);
        ownerUserList.add(ownerUser);
      }
    }
  }
  List<Record> relations=Db.find("select * FROM 72crm_task_relation where task_id = ?",taskId);
  if (relations != null) {
    for (    Record relation : relations) {
      List<Record> customerList=new ArrayList<>();
      List<Record> contactsList=new ArrayList<>();
      List<Record> businessList=new ArrayList<>();
      List<Record> contractList=new ArrayList<>();
      if (StrUtil.isNotBlank(relation.getStr("customer_ids"))) {
        String[] customerIds=relation.getStr("customer_ids").split(",");
        for (        String customerId : customerIds) {
          if (StrUtil.isNotBlank(customerId)) {
            Record customer=Db.findFirst("select customer_id,customer_name  from 72crm_crm_customer where customer_id = ?",customerId);
            if (customer != null) {
              customerList.add(customer);
            }
          }
        }
      }
      if (StrUtil.isNotBlank(relation.getStr("contacts_ids"))) {
        String[] contactsIds=relation.getStr("contacts_ids").split(",");
        for (        String contactsId : contactsIds) {
          if (StrUtil.isNotBlank(contactsId)) {
            Record contacts=Db.findFirst("select contacts_id,name from 72crm_crm_contacts  where contacts_id = ?",contactsId);
            if (contacts != null) {
              contactsList.add(contacts);
            }
          }
        }
      }
      if (StrUtil.isNotBlank(relation.getStr("business_ids"))) {
        String[] businessIds=relation.getStr("business_ids").split(",");
        for (        String businessId : businessIds) {
          if (StrUtil.isNotBlank(businessId)) {
            Record business=Db.findFirst("select business_id,business_name  from 72crm_crm_business  where business_id = ?",businessId);
            if (business != null) {
              businessList.add(business);
            }
          }
        }
      }
      if (StrUtil.isNotBlank(relation.getStr("contract_ids"))) {
        String[] contractIds=relation.getStr("contract_ids").split(",");
        for (        String contractId : contractIds) {
          if (StrUtil.isNotBlank(contractId)) {
            Record contract=Db.findFirst("select contract_id,name from 72crm_crm_contract  where contract_id = ?",contractId);
            if (contract != null) {
              contractList.add(contract);
            }
          }
        }
        task.set("contractList",contractList);
      }
      task.set("customerList",customerList);
      task.set("contactsList",contactsList);
      task.set("businessList",businessList);
      task.set("contractList",contractList);
    }
  }
  return task.set("labelList",labelList).set("ownerUserList",ownerUserList);
}
