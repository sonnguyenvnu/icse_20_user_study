/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
package jetbrains.mps.lang.editor.menus.transformation;

import jetbrains.mps.editor.runtime.completion.CompletionItemInformation;
import jetbrains.mps.editor.runtime.completion.CompletionMenuItemCustomizationContext;
import jetbrains.mps.editor.runtime.menus.EditorMenuItemCompositeCustomizationContext;
import jetbrains.mps.editor.runtime.menus.EditorMenuItemModifyingCustomizationContext;
import jetbrains.mps.lang.editor.menus.substitute.SubstituteMenuContextToEditorMenuItemModifyingCustomizationContext;
import jetbrains.mps.openapi.editor.menus.style.EditorMenuItemCustomizer;
import jetbrains.mps.openapi.editor.menus.style.EditorMenuItemStyle;
import jetbrains.mps.openapi.editor.menus.substitute.SubstituteMenuContext;
import jetbrains.mps.openapi.editor.menus.substitute.SubstituteMenuItem;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeUtil;

/**
 * @author simon
 */
public class DefaultSubstituteMenuItemAsActionItem extends SubstituteMenuItemAsActionItem {
  private static final Logger LOG = Logger.getLogger(DefaultSubstituteMenuItemAsActionItem.class);

  private final SubstituteMenuContext myContext;

  public DefaultSubstituteMenuItemAsActionItem(SubstituteMenuItem substituteItem, SubstituteMenuContext context) {
    super(substituteItem);
    myContext = context;
  }


  @Override
  public void execute(@NotNull String pattern) {
    SNode newChild = getSubstituteItem().createNode(pattern);
    final SNode nodeToSelect = doSubstitute(newChild);
    if (nodeToSelect != null) {
      myContext.getEditorContext().flushEvents();
      select(nodeToSelect, pattern);
    }
  }

  protected void select(SNode newChild, @NotNull String pattern) {
    getSubstituteItem().select(newChild, pattern);
  }


  protected SNode doSubstitute(SNode newChild) {
    if (newChild != null) {
      SContainmentLink containmentLink = myContext.getLink();
      SNode parentNode = myContext.getParentNode();
      SNode currentChild = myContext.getCurrentTargetNode();
      if (containmentLink == null) {
        LOG.error("Containment link should not be null. " + "Parent node" + parentNode.getPresentation() + " Parent id: " + parentNode.getNodeId());
        return null;
      }
      if (!newChild.getConcept().isSubConceptOf(containmentLink.getTargetConcept())) {
        LOG.error("couldn't set instance of " + newChild.getConcept().getName() +
                  " as child '" + containmentLink.getName() + "' to parent" + parentNode.getPresentation() + " Parent id: " + parentNode.getNodeId());
        return null;
      }
      if (currentChild == null) {
        parentNode.addChild(containmentLink, newChild);
      } else {
        SNodeUtil.replaceWithAnother(currentChild, newChild);
        currentChild.delete();
      }
    }
    return newChild;
  }

  @Override
  public void customize(String pattern, EditorMenuItemStyle style) {
    super.customize(pattern, style);
    if (myContext.getCurrentTargetNode() != null) {

      EditorMenuItemModifyingCustomizationContext
          context = new EditorMenuItemModifyingCustomizationContext(myContext.getCurrentTargetNode(), null, null, null);
      CompletionItemInformation completionItemInformation =
          new CompletionItemInformation(null, null, getMatchingText(pattern), getShortDescriptionText(pattern));
      EditorMenuItemCompositeCustomizationContext compositeContext =
          new EditorMenuItemCompositeCustomizationContext(context, new CompletionMenuItemCustomizationContext(completionItemInformation));
      for (EditorMenuItemCustomizer customizer : myContext.getCustomizers()) {
        customizer.customize(style, compositeContext);
      }
    }
  }

  protected SubstituteMenuContext getSubstituteMenuContext() {
    return myContext;
  }
}
