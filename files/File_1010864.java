/*
 * Copyright 2003-2013 JetBrains s.r.o.
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

package jetbrains.mps.idea.core.psi.impl;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiReference;
import com.intellij.util.IncorrectOperationException;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.smodel.SNodePointer;
import jetbrains.mps.util.Computable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SReferenceLink;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNodeId;

/**
 * evgeny, 1/25/13
 */
public class MPSPsiRef extends MPSPsiNodeBase {

  private final SReferenceLink role;
  private SModelReference model;
  private SNodeId nodeId;
  private String referenceText;

  public MPSPsiRef(SReferenceLink role, SModelReference model, SNodeId nodeId, PsiManager manager) {
    super(manager);
    this.role = role;
    this.model = model;
    this.nodeId = nodeId;
  }

  public MPSPsiRef(SReferenceLink role, String referenceText, PsiManager manager) {
    super(manager);
    this.role = role;
    this.referenceText = referenceText;
  }

  public SReferenceLink getRole() {
    return role;
  }

  public PsiElement resolve() {
    // Note: we expect that PSI clients do take read lock to resolve references
    ApplicationManager.getApplication().assertReadAccessAllowed();

    return new ModelAccessHelper(ProjectHelper.getModelAccess(getProject())).runReadAction(new Computable<PsiElement>() {
      @Override
      public PsiElement compute() {
        if (model != null && nodeId != null) {
          return MPSPsiProvider.getInstance(getProject()).getPsi(new SNodePointer(model, nodeId));
        } else {
          // TODO dynamic ref
          return null;
        }
      }
    });
  }

  @Override
  public PsiFile getContainingFile() {
    return getContainingRoot();
  }

  public SModelReference getModelReference() {
    return model;
  }

  public SNodeId getNodeId() {
    return nodeId;
  }

  public String getReferenceText() {
    return referenceText;
  }

  @Override
  public PsiReference[] getReferences() {
    return new PsiReference[]{getReference()};
  }


  @Override
  public String toString() {
    return "MPSPsiRef: reference in " + role;
  }

  @Override
  public boolean isWritable() {
    return true;
  }

  @Override
  public String getText() {
    if (referenceText != null) {
      return referenceText;
    }

    PsiElement parent = getParent();
    if (parent != null) {
      return parent.getText();
    }

    return "";
  }

  @Override
  public PsiReference getReference() {
    return new MPSPsiReference();
  }

  public class MPSPsiReference implements PsiReference {
    @Override
    public PsiElement getElement() {
      return MPSPsiRef.this;
    }

    @Override
    public TextRange getRangeInElement() {
      return TextRange.EMPTY_RANGE;
    }

    @Nullable
    @Override
    public PsiElement resolve() {
      return MPSPsiRef.this.resolve();
    }

    @NotNull
    @Override
    public String getCanonicalText() {
      return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
      throw new IncorrectOperationException();

    }

    @Override
    public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
      throw new IncorrectOperationException();
    }

    @Override
    public boolean isReferenceTo(PsiElement element) {
      PsiElement target = resolve();
      // Just == ?
      return target != null && target == element;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
      return new Object[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isSoft() {
      return false;
    }
  }

}
