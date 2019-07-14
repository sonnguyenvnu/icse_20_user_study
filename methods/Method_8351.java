private boolean isDefaultAdminRights(){
  return adminRights.change_info && adminRights.delete_messages && adminRights.ban_users && adminRights.invite_users && adminRights.pin_messages && !adminRights.add_admins || !adminRights.change_info && !adminRights.delete_messages && !adminRights.ban_users && !adminRights.invite_users && !adminRights.pin_messages && !adminRights.add_admins;
}
