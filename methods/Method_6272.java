private static boolean isBannableAction(int action){
switch (action) {
case ACTION_PIN:
case ACTION_CHANGE_INFO:
case ACTION_INVITE:
case ACTION_SEND:
case ACTION_SEND_MEDIA:
case ACTION_SEND_STICKERS:
case ACTION_EMBED_LINKS:
case ACTION_SEND_POLLS:
case ACTION_VIEW:
    return true;
}
return false;
}
