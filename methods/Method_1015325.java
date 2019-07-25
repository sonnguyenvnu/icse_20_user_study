public void init(){
  recentModel=new ContactTreeMode(root1);
  friendModel=new ContactTreeMode(root2);
  discussModel=new ContactTreeMode(root4);
  recentTree.setModel(recentModel);
  friendTree.setModel(friendModel);
  discussTree.setModel(discussModel);
}
