public Collection<Action> make(API api,Container.Entry entry,String fragment){
  return Collections.<Action>singletonList(new CopyQualifiedNameAction(api,entry,fragment));
}
