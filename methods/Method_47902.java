@Override public Repository<RepetitionRecord> buildRepetitionListRepository(){
  return new Repository<>(RepetitionRecord.class,db);
}
