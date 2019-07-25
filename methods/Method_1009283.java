public String name(){
  OtpErlangTuple quotedDefmodule=(OtpErlangTuple)defmodule.quote();
  OtpErlangList callArguments=Macro.INSTANCE.callArguments(quotedDefmodule);
  assert callArguments.arity() == 2;
  OtpErlangObject quotedName=callArguments.elementAt(0);
  assert Macro.INSTANCE.isAliases(quotedName);
  return Macro.INSTANCE.toString(quotedName);
}
