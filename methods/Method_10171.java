private Object readResolve(){
  return MailSenderHolder.INSTANCE;
}
