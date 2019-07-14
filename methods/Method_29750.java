protected AccountUserResource setArguments(Account account){
  SimpleUser partialUser=makePartialUser(account);
  super.setArguments(partialUser.getIdOrUid(),partialUser,AccountUtils.getUser(account));
  FragmentUtils.getArgumentsBuilder(this).putParcelable(EXTRA_ACCOUNT,account);
  return this;
}
