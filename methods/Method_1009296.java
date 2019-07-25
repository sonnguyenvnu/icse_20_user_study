@NotNull @Override public org.elixir_lang.psi.stub.UnmatchedUnqualifiedNoArgumentsCall deserialize(@NotNull StubInputStream dataStream,@Nullable StubElement parentStub) throws IOException {
  Deserialized deserialized=Deserialized.deserialize(dataStream);
  return new org.elixir_lang.psi.stub.UnmatchedUnqualifiedNoArgumentsCall(parentStub,this,deserialized);
}
