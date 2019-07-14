@Override protected void onHandleIntent(@Nullable Intent intent){
  Login login=Login.getUser();
  if (login != null) {
    SlackInvitePostModel body=new SlackInvitePostModel();
    body.setEmail(login.getEmail());
    body.setFirst_name(login.getName());
    body.setLast_name(login.getLogin());
    RxHelper.getObservable(RestProvider.getSlackService().invite(body)).subscribe(response -> {
      if (response != null) {
        if (response.isOk()) {
          Toasty.success(App.getInstance(),getString(R.string.successfully_invited)).show();
        }
 else {
          Toasty.info(App.getInstance(),response.getError().replaceAll("_"," ")).show();
        }
      }
    }
,Throwable::printStackTrace);
  }
}
