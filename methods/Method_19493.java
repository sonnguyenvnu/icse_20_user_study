@OnEvent(RenderEvent.class) static RenderInfo onRender(ComponentContext c,@FromEvent int index){
  final int numDemos=5;
  Component component;
switch (index % numDemos) {
case 0:
    component=StoryFooterComponent.create(c).key("footer").build();
  break;
case 1:
component=UpDownBlocksComponent.create(c).build();
break;
case 2:
component=LeftRightBlocksComponent.create(c).build();
break;
case 3:
component=OneByOneLeftRightBlocksComponent.create(c).build();
break;
case 4:
component=LeftRightBlocksSequenceComponent.create(c).build();
break;
default :
throw new RuntimeException("Bad index: " + index);
}
return ComponentRenderInfo.create().component(component).build();
}
