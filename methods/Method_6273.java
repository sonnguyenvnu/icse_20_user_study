private static boolean isAdminAction(int action){
switch (action) {
case ACTION_PIN:
case ACTION_CHANGE_INFO:
case ACTION_INVITE:
case ACTION_ADD_ADMINS:
case ACTION_POST:
case ACTION_EDIT_MESSAGES:
case ACTION_DELETE_MESSAGES:
case ACTION_BLOCK_USERS:
    return true;
}
return false;
}
