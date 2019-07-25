@Override public int update(){
  session.commit(true);
  if (view == null && ifExists) {
    return 0;
  }
  session.getUser().checkRight(view,Right.ALL);
  DbException e=view.recompile(session,false,true);
  if (e != null) {
    throw e;
  }
  return 0;
}
