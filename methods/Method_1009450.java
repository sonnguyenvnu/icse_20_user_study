public void put(Element element){
switch (element.getType()) {
case NODE:
    nodeDao.put((Node)element);
  break;
case WAY:
wayDao.put((Way)element);
break;
case RELATION:
relationDao.put((Relation)element);
break;
}
}
