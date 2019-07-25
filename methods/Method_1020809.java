public RedisExecProvider override(OS os,Architecture arch,String executable){
  Preconditions.checkNotNull(executable);
  executables.put(new OsArchitecture(os,arch),executable);
  return this;
}
