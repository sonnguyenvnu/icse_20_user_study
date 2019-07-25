@Override public TableRow<S> call(TableView<S> tableView){
  TableRow<S> row=new TableRow<>();
  if (toTooltip != null) {
    String tooltipText=toTooltip.call(row.getItem());
    if (StringUtil.isNotBlank(tooltipText)) {
      row.setTooltip(new Tooltip(tooltipText));
    }
  }
  if (onMouseClickedEvent != null) {
    row.setOnMouseClicked(event -> {
      if (!row.isEmpty()) {
        onMouseClickedEvent.accept(row.getItem(),event);
      }
    }
);
  }
  if (contextMenuFactory != null) {
    row.setOnContextMenuRequested(event -> {
      if (!row.isEmpty()) {
        row.setContextMenu(contextMenuFactory.apply(row.getItem()));
        row.getContextMenu().show(row,event.getScreenX(),event.getScreenY());
      }
      event.consume();
    }
);
    tableView.addEventHandler(KeyEvent.KEY_RELEASED,event -> {
      boolean rowFocused=!row.isEmpty() && tableView.getFocusModel().getFocusedIndex() == row.getIndex();
      if (event.getCode() == KeyCode.CONTEXT_MENU && rowFocused) {
        Bounds anchorBounds=row.getBoundsInParent();
        double x=anchorBounds.getMinX() + anchorBounds.getWidth() / 2;
        double y=anchorBounds.getMinY() + anchorBounds.getHeight() / 2;
        Point2D screenPosition=row.getParent().localToScreen(x,y);
        if (row.getContextMenu() == null) {
          row.setContextMenu(contextMenuFactory.apply(row.getItem()));
        }
        row.getContextMenu().show(row,screenPosition.getX(),screenPosition.getY());
      }
    }
);
  }
  if (toOnDragDetected != null) {
    row.setOnDragDetected(event -> {
      if (!row.isEmpty()) {
        toOnDragDetected.accept(row,row.getItem(),event);
      }
    }
);
  }
  if (toOnDragDropped != null) {
    row.setOnDragDropped(event -> {
      if (!row.isEmpty()) {
        toOnDragDropped.accept(row.getItem(),event);
      }
    }
);
  }
  if (toOnDragEntered != null) {
    row.setOnDragEntered(event -> {
      if (!row.isEmpty()) {
        toOnDragEntered.accept(row.getItem(),event);
      }
    }
);
  }
  if (toOnDragExited != null) {
    row.setOnDragExited(event -> {
      if (!row.isEmpty()) {
        toOnDragExited.accept(row.getItem(),event);
      }
    }
);
  }
  if (toOnDragOver != null) {
    row.setOnDragOver(event -> {
      if (!row.isEmpty()) {
        toOnDragOver.accept(row.getItem(),event);
      }
    }
);
  }
  if (toOnMouseDragEntered != null) {
    row.setOnMouseDragEntered(event -> {
      if (!row.isEmpty()) {
        toOnMouseDragEntered.accept(row,row.getItem(),event);
      }
    }
);
  }
  return row;
}
