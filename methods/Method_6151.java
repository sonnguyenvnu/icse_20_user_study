public static boolean handleProxyIntent(Activity activity,Intent intent){
  if (intent == null) {
    return false;
  }
  try {
    if ((intent.getFlags() & Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY) != 0) {
      return false;
    }
    Uri data=intent.getData();
    if (data != null) {
      String user=null;
      String password=null;
      String port=null;
      String address=null;
      String secret=null;
      String scheme=data.getScheme();
      if (scheme != null) {
        if ((scheme.equals("http") || scheme.equals("https"))) {
          String host=data.getHost().toLowerCase();
          if (host.equals("telegram.me") || host.equals("t.me") || host.equals("telegram.dog")) {
            String path=data.getPath();
            if (path != null) {
              if (path.startsWith("/socks") || path.startsWith("/proxy")) {
                address=data.getQueryParameter("server");
                port=data.getQueryParameter("port");
                user=data.getQueryParameter("user");
                password=data.getQueryParameter("pass");
                secret=data.getQueryParameter("secret");
              }
            }
          }
        }
 else         if (scheme.equals("tg")) {
          String url=data.toString();
          if (url.startsWith("tg:proxy") || url.startsWith("tg://proxy") || url.startsWith("tg:socks") || url.startsWith("tg://socks")) {
            url=url.replace("tg:proxy","tg://telegram.org").replace("tg://proxy","tg://telegram.org").replace("tg://socks","tg://telegram.org").replace("tg:socks","tg://telegram.org");
            data=Uri.parse(url);
            address=data.getQueryParameter("server");
            port=data.getQueryParameter("port");
            user=data.getQueryParameter("user");
            password=data.getQueryParameter("pass");
            secret=data.getQueryParameter("secret");
          }
        }
      }
      if (!TextUtils.isEmpty(address) && !TextUtils.isEmpty(port)) {
        if (user == null) {
          user="";
        }
        if (password == null) {
          password="";
        }
        if (secret == null) {
          secret="";
        }
        showProxyAlert(activity,address,port,user,password,secret);
        return true;
      }
    }
  }
 catch (  Exception ignore) {
  }
  return false;
}
