@NotNull @Override public org.elixir_lang.psi.stub.UnmatchedQualifiedNoParenthesesCall deserialize(@NotNull StubInputStream dataStream,@Nullable StubElement parentStub) throws IOException {
  Deserialized deserialized=Deserialized.deserialize(dataStream);
  return new org.elixir_lang.psi.stub.UnmatchedQualifiedNoParenthesesCall(parentStub,this,deserialized);
}
