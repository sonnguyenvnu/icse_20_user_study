public boolean putUser(TLRPC.User user,boolean fromCache){
  if (user == null) {
    return false;
  }
  fromCache=fromCache && user.id / 1000 != 333 && user.id != 777000;
  TLRPC.User oldUser=users.get(user.id);
  if (oldUser == user) {
    return false;
  }
  if (oldUser != null && !TextUtils.isEmpty(oldUser.username)) {
    objectsByUsernames.remove(oldUser.username.toLowerCase());
  }
  if (!TextUtils.isEmpty(user.username)) {
    objectsByUsernames.put(user.username.toLowerCase(),user);
  }
  if (user.min) {
    if (oldUser != null) {
      if (!fromCache) {
        if (user.bot) {
          if (user.username != null) {
            oldUser.username=user.username;
            oldUser.flags|=8;
          }
 else {
            oldUser.flags=oldUser.flags & ~8;
            oldUser.username=null;
          }
        }
        if (user.photo != null) {
          oldUser.photo=user.photo;
          oldUser.flags|=32;
        }
 else {
          oldUser.flags=oldUser.flags & ~32;
          oldUser.photo=null;
        }
      }
    }
 else {
      users.put(user.id,user);
    }
  }
 else {
    if (!fromCache) {
      users.put(user.id,user);
      if (user.id == UserConfig.getInstance(currentAccount).getClientUserId()) {
        UserConfig.getInstance(currentAccount).setCurrentUser(user);
        UserConfig.getInstance(currentAccount).saveConfig(true);
      }
      if (oldUser != null && user.status != null && oldUser.status != null && user.status.expires != oldUser.status.expires) {
        return true;
      }
    }
 else     if (oldUser == null) {
      users.put(user.id,user);
    }
 else     if (oldUser.min) {
      user.min=false;
      if (oldUser.bot) {
        if (oldUser.username != null) {
          user.username=oldUser.username;
          user.flags|=8;
        }
 else {
          user.flags=user.flags & ~8;
          user.username=null;
        }
      }
      if (oldUser.photo != null) {
        user.photo=oldUser.photo;
        user.flags|=32;
      }
 else {
        user.flags=user.flags & ~32;
        user.photo=null;
      }
      users.put(user.id,user);
    }
  }
  return false;
}
