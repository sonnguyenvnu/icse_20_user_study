private ImageReceiver getImageReceiverView(View view,TLRPC.PageBlock pageBlock,int[] coords){
  if (view instanceof BlockPhotoCell) {
    BlockPhotoCell cell=(BlockPhotoCell)view;
    if (cell.currentBlock == pageBlock) {
      view.getLocationInWindow(coords);
      return cell.imageView;
    }
  }
 else   if (view instanceof BlockVideoCell) {
    BlockVideoCell cell=(BlockVideoCell)view;
    if (cell.currentBlock == pageBlock) {
      view.getLocationInWindow(coords);
      return cell.imageView;
    }
  }
 else   if (view instanceof BlockCollageCell) {
    ImageReceiver imageReceiver=getImageReceiverFromListView(((BlockCollageCell)view).innerListView,pageBlock,coords);
    if (imageReceiver != null) {
      return imageReceiver;
    }
  }
 else   if (view instanceof BlockSlideshowCell) {
    ImageReceiver imageReceiver=getImageReceiverFromListView(((BlockSlideshowCell)view).innerListView,pageBlock,coords);
    if (imageReceiver != null) {
      return imageReceiver;
    }
  }
 else   if (view instanceof BlockListItemCell) {
    BlockListItemCell blockListItemCell=(BlockListItemCell)view;
    if (blockListItemCell.blockLayout != null) {
      ImageReceiver imageReceiver=getImageReceiverView(blockListItemCell.blockLayout.itemView,pageBlock,coords);
      if (imageReceiver != null) {
        return imageReceiver;
      }
    }
  }
 else   if (view instanceof BlockOrderedListItemCell) {
    BlockOrderedListItemCell blockOrderedListItemCell=(BlockOrderedListItemCell)view;
    if (blockOrderedListItemCell.blockLayout != null) {
      ImageReceiver imageReceiver=getImageReceiverView(blockOrderedListItemCell.blockLayout.itemView,pageBlock,coords);
      if (imageReceiver != null) {
        return imageReceiver;
      }
    }
  }
  return null;
}
