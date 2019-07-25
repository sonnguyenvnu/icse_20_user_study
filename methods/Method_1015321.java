public void init(){
  recentModel=new ContactTreeMode(root1);
  friendModel=new ContactTreeMode(root2);
  groupModel=new ContactTreeMode(root3);
  discussModel=new ContactTreeMode(root4);
  recentTree.setModel(recentModel);
  friendTree.setModel(friendModel);
  groupTree.setModel(groupModel);
  discussTree.setModel(discussModel);
}
