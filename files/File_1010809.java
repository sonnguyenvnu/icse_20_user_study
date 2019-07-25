/*
 * Copyright 2003-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.mps.nodeEditor.updater;

import jetbrains.mps.nodeEditor.ReferencedNodeContext;
import jetbrains.mps.nodeEditor.memory.MemoryAnalyzer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * User: shatalin
 * Date: 22/12/15
 */
class UpdateInfoNode {
  private static final Logger LOG = LogManager.getLogger(UpdateInfoNode.class);

  private final ReferencedNodeContext myContext;
  private UpdateInfoNode myParent;
  private final Collection<UpdateInfoNode> myChildren = new ArrayList<>();

  UpdateInfoNode(ReferencedNodeContext context) {
    myContext = context;
    myParent = null;
  }

  UpdateInfoNode(ReferencedNodeContext context, UpdateInfoNode parent) {
    myContext = context;
    myParent = parent;
    parent.addChild(this);
  }

  private void addChild(UpdateInfoNode child) {
    myChildren.add(child);
  }

  private void removeChild(UpdateInfoNode child) {
    myChildren.remove(child);
  }

  protected void attachNewParent(@NotNull UpdateInfoNode parent) {
    assert myParent == null;
    myParent = parent;
    parent.addChild(this);
  }

  @NotNull
  private UpdateInfoNode detachFromParent() {
    assert myParent != null;
    UpdateInfoNode parent = myParent;
    myParent.removeChild(this);
    myParent = null;
    return parent;
  }

  @NotNull
  UpdateInfoNode replace(@NotNull UpdateInfoNode replacement) {
    assert replacement.getParent() == null;
    validateBeforeReplace(replacement);

    if (getParent() != null) {
      replacement.attachNewParent(detachFromParent());
    }

    return replacement;
  }

  protected void validateBeforeReplace(UpdateInfoNode replacement) {
    // In case of node attribute, we create separate sub-class of this class: UpdateInfoNodeAttribute
    assert !getContext().isNodeAttribute();
    assert !replacement.getContext().isNodeAttribute();
  }

  @NotNull
  UpdateInfoNode replaceByNodeAttributeInfo(@NotNull ReferencedNodeContext context) {
    assert context.isNodeAttribute();
    return new UpdateInfoNodeAttribute(context);
  }

  @NotNull
  UpdateInfoNode detach() {
    if (getParent() != null) {
      detachFromParent();
    }
    return this;
  }

  UpdateInfoNode getParent() {
    return myParent;
  }

  Collection<UpdateInfoNode> getChildren() {
    return Collections.unmodifiableCollection(myChildren);
  }

  ReferencedNodeContext getContext() {
    return myContext;
  }

  void calculateSize(@NotNull MemoryAnalyzer analyzer) {
    analyzer.appendObject(this);
    analyzer.appendCollection(myChildren);
    myContext.calculateSize(analyzer);
    for (UpdateInfoNode child : getChildren()) {
      child.calculateSize(analyzer);
    }
  }

  void keepAttributedInfo() {
  }

  private class UpdateInfoNodeAttribute extends UpdateInfoNode {
    private boolean myAttributeInfoKept;

    private UpdateInfoNodeAttribute(ReferencedNodeContext context) {
      super(context);
      if (UpdateInfoNode.this.getParent() != null) {
        attachNewParent(UpdateInfoNode.this.detachFromParent());
      }
    }

    @NotNull
    @Override
    UpdateInfoNode replace(@NotNull UpdateInfoNode replacement) {
      super.replace(replacement);

      if (((UpdateInfoNodeAttribute) replacement).myAttributeInfoKept) {
        keepAttributedInfo();
      }
      for (UpdateInfoNode childInfo : new ArrayList<>(getChildren())) {
        childInfo.detachFromParent();
        childInfo.attachNewParent(replacement);
      }
      return replacement;
    }

    @Override
    void keepAttributedInfo() {
      if (myAttributeInfoKept) {
        return;
      }
      UpdateInfoNode.this.attachNewParent(this);
      myAttributeInfoKept = true;
    }

    @Override
    protected void validateBeforeReplace(UpdateInfoNode replacement) {
      // UpdateInfoNodeAttribute may be replaced only by UpdateInfoNodeAttribute
      assert replacement instanceof UpdateInfoNodeAttribute;

      SNode attributedNode = replacement.getContext().getNode().getParent();
      if (attributedNode != null) {
        // For node attributes, in case of reusing UpdateInfoNodeAttribute:
        //
        // UpdateInfoNode of the attributed node should be reused as well.
        // In accordance with current updater logic, at this moment it should
        // be already removed from the list of children of the reused
        // UpdateInfoNodeAttribute for the attribute.
        replacement.getChildren().stream().filter(ci -> ci.getContext().getNode() == attributedNode).findFirst().ifPresent(ci -> LOG.error(
            "Invalid child UpdateInfoNode (node: " + attributedNode + "<" + attributedNode.getConcept() +
                ">) present in UpdateInfoNode for node attribute: (node: " + replacement.getContext().getNode() + "<" +
                replacement.getContext().getNode().getConcept() + ">)", new Throwable()));
      } else {
        LOG.error(
            "Attributed node expected, but is null (node: " + replacement.getContext().getNode() + "<" + replacement.getContext().getNode().getConcept() + ">",
            new Throwable());
      }
    }
  }
}

