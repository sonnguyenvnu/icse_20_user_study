private void transfer(List<Record> recordList){
  recordList.forEach(record -> {
    setRelation(record);
    record.set("createUser",Db.findFirst("select user_id,realname,img from 72crm_admin_user where user_id = ?",record.getInt("create_user_id")));
    String batchId=record.getStr("batch_id");
    adminFileService.queryByBatchId(batchId,record);
    setCountRecord(record);
    List<Integer> roles=BaseUtil.getUser().getRoles();
    Map<String,Integer> permission=new HashMap<>();
    Integer createUserId=record.getInt("create_user_id");
    Integer examineStatus=record.getInt("examine_status");
    Long userId=BaseUtil.getUser().getUserId();
    if ((userId.equals(BaseConstant.SUPER_ADMIN_USER_ID) && (examineStatus == 4 || examineStatus == 2)) || (createUserId.equals(BaseUtil.getUser().getUserId().intValue()) && (examineStatus == 4 || examineStatus == 2))) {
      permission.put("isUpdate",1);
    }
 else {
      permission.put("isUpdate",0);
    }
    if (roles.contains(BaseConstant.SUPER_ADMIN_ROLE_ID) && examineStatus != 1) {
      permission.put("isDelete",1);
    }
 else {
      permission.put("isDelete",0);
    }
    if (((userId.equals(BaseConstant.SUPER_ADMIN_USER_ID) || userId.equals(record.getInt("create_user_id"))) && (examineStatus == 0 || examineStatus == 3)) && examineStatus != 4) {
      permission.put("isChecked",1);
    }
 else {
      permission.put("isChecked",0);
    }
    record.set("permission",permission);
  }
);
}
