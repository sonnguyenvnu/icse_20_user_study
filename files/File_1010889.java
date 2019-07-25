/*
 * Copyright 2003-2019 JetBrains s.r.o.
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
package jetbrains.mps.idea.java.usages;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.IncorrectOperationException;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.idea.core.psi.impl.MPSPsiNode;
import jetbrains.mps.idea.core.psi.impl.MPSPsiProvider;
import jetbrains.mps.idea.core.refactoring.NodePtr;
import jetbrains.mps.idea.java.psiStubs.JavaForeignIdBuilder;
import jetbrains.mps.idea.java.refactoring.MoveRenameBatch;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.smodel.ModelImports;
import jetbrains.mps.smodel.SNodeId.Foreign;
import jetbrains.mps.smodel.SNodePointer;
import jetbrains.mps.smodel.StaticReference;
import jetbrains.mps.util.Computable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SReferenceLink;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeId;
import org.jetbrains.mps.openapi.model.SNodeReference;


/**
 * danilla 3/24/13
 */

public class IdPrefixReference implements PsiReference {
  private SNodeReference myTarget;
  private SReferenceLink myAssociationLink;
  private PsiElement myParent;

  public IdPrefixReference(SNodeReference target, SReferenceLink association, PsiElement fosterFather) {
    myTarget = target;
    myAssociationLink = association;
    myParent = fosterFather;
  }

  @Override
  public PsiElement getElement() {
    return myParent;
  }

  @Override
  public TextRange getRangeInElement() {
    return TextRange.EMPTY_RANGE;
  }

  @Nullable
  @Override
  public PsiElement resolve() {
    ApplicationManager.getApplication().assertReadAccessAllowed();

    return new ModelAccessHelper(ProjectHelper.getModelAccess(myParent.getProject())).runReadAction(new Computable<PsiElement>() {
      @Override
      public PsiElement compute() {
        return MPSPsiProvider.getInstance(myParent.getProject()).getPsi(myTarget);
      }
    });
  }

  @NotNull
  @Override
  public String getCanonicalText() {
    return "MPS id prefix reference";
  }

  @Override
  public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
    SNodeId targetNodeId = myTarget.getNodeId();
    String str = targetNodeId.toString();
    String newStr;
    int lastDot = str.lastIndexOf(".");
    if (lastDot < 0) {
      newStr = Foreign.ID_PREFIX + newElementName;
    } else {
      newStr = str.substring(0, lastDot + 1) + newElementName;
    }
    final NodePtr newTarget = new NodePtr(myTarget.getModelReference(), new Foreign(newStr));

    SNodeReference source = ((MPSPsiNode) myParent).getSNodeReference();

    final MoveRenameBatch mrb = myParent.getProject().getComponent(MoveRenameBatch.class);
    mrb.scheduleIdPrefixRefUpdate(source, myAssociationLink, () -> handleRename(newTarget));
    return myParent;
  }

  @Override
  public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
    SNodeReference source = ((MPSPsiNode) myParent).getSNodeReference();
    final NodePtr newTargetPtr = JavaForeignIdBuilder.computeNodePtr(element);
    final MoveRenameBatch mrb = myParent.getProject().getComponent(MoveRenameBatch.class);
    mrb.scheduleIdPrefixRefUpdate(source, myAssociationLink, () -> handleRename(newTargetPtr));
    return myParent;
  }

  private void handleRename(NodePtr newNode) {
    SNodePointer oldNode = (SNodePointer) myTarget;
    SNode source = ((MPSPsiNode) myParent).getSNodeReference().resolve(ProjectHelper.getProjectRepository(myParent.getProject()));
    String oldId = source.getReference(myAssociationLink).getTargetNodeId().toString();

    // replacing all proper occurences
    String what = oldNode.getNodeId().toString();
    what = what.startsWith(Foreign.ID_PREFIX) ? what.substring(1) : what;
    String replacement = newNode.getNodeId().toString();
    replacement = replacement.startsWith(Foreign.ID_PREFIX) ? replacement.substring(1) : replacement;

    String newId = carefullyReplace(oldId, what, replacement);

    source.setReference(myAssociationLink, StaticReference.create(myAssociationLink, source, newNode.getSModelReference(), new Foreign(newId)));

    // add model import if needed
    if (!oldNode.getModelReference().equals(newNode.getSModelReference())) {
      SModel model = ((MPSPsiNode) myParent).getSNodeReference().resolve(ProjectHelper.getProjectRepository(myParent.getProject())).getModel();
      SModelReference newTargetModel = newNode.getSModelReference();

      new ModelImports(model).addModelImport(newTargetModel);
    }
  }

  private String carefullyReplace(String input, String what, String replacement) {
    String result = input;

    int len = what.length();
    int idx = input.indexOf(what);

    while (idx >= 0) {
      if (idx != 0) {
        char c = result.charAt(idx - 1);
        if (c == '.' || Character.isLetterOrDigit(c)) { // java foreign id specific knowledge
          idx = result.indexOf(what, idx + 1);
          continue;
        }
      }
      result = result.substring(0, idx) + replacement + result.substring(idx + len);
      idx = result.indexOf(what, idx + len);
    }

    return result;
  }

  @Override
  public boolean isReferenceTo(PsiElement element) {
    if (!(element instanceof MPSPsiNode)) return false;
    return myTarget.equals(((MPSPsiNode) element).getSNodeReference());
  }

  @NotNull
  @Override
  public Object[] getVariants() {
    return new Object[0];  //To change body of implemented methods use File | Settings | File Templates.
  }

  // Q: should it be true?
  @Override
  public boolean isSoft() {
    return false;
  }
}
