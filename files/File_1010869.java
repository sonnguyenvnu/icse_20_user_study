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

package jetbrains.mps.idea.core.psi;

import com.intellij.lang.Language;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiReference;
import com.intellij.psi.SingleRootFileViewProvider;
import jetbrains.mps.fileTypes.MPSLanguage;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.idea.core.psi.impl.MPSPsiModel;
import jetbrains.mps.idea.core.psi.impl.MPSPsiProvider;
import jetbrains.mps.idea.core.psi.impl.MPSPsiRootNode;
import jetbrains.mps.nodefs.MPSNodeVirtualFile;
import jetbrains.mps.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.module.SRepository;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by danilla on 12/19/14.
 */
public class MPSNodeFileViewProvider extends SingleRootFileViewProvider {
  private final static boolean EVENT_SYSTEM_ENABLED = false;
  private final static Language LANGUAGE = MPSLanguage.INSTANCE;

  public MPSNodeFileViewProvider(@NotNull PsiManager manager, @NotNull MPSNodeVirtualFile nodeFile) {
    super(manager, nodeFile, EVENT_SYSTEM_ENABLED, LANGUAGE);
  }

  @Nullable
  @Override
  public Document getDocument() {
    return null;
  }

  @NotNull
  @Override
  public CharSequence getContents() {
    MPSPsiRootNode psiFile = (MPSPsiRootNode) getPsiInner(getBaseLanguage());
    MPSPsiModel psiModel = psiFile.getContainingModel();
    return new FakeTextContents(psiModel.getMaxNodePosition());
  }

  @NotNull
  @Override
  public Language getBaseLanguage() {
    return LANGUAGE;
  }

  @NotNull
  @Override
  public Set<Language> getLanguages() {
    return Collections.singleton(getBaseLanguage());
  }

  @Override
  public PsiFile getPsiInner(@NotNull Language target) {
    // todo assert idea read

    if (target != getBaseLanguage()) {
      return null;
    }

    Project p = ProjectHelper.fromIdeaProject(getManager().getProject());
    assert p != null;
    final SRepository repo = p.getRepository();
    final Ref<PsiElement> result = new Ref<>(null);
    // todo use MPSNodeVirtualFile.getNode() (rewrite it to project repo)
    repo.getModelAccess().runReadAction(() -> {
      SNode sNode = ((MPSNodeVirtualFile) getVirtualFile()).getSNodePointer().resolve(repo);
      if (sNode != null) {
        result.set(MPSPsiProvider.getInstance(getManager().getProject()).getPsi(sNode));
      }
    });

    if (result.get() == null) {
      return null;
    }
    PsiFile psiFile = result.get().getContainingFile();
    assert psiFile instanceof MPSPsiRootNode;
    return psiFile;
  }

  @NotNull
  @Override
  public List<PsiFile> getAllFiles() {
    return Collections.singletonList(getPsi(getBaseLanguage()));
  }

  @Override
  public boolean isEventSystemEnabled() {
    return EVENT_SYSTEM_ENABLED;
  }

  @Override
  public boolean isPhysical() {
    return false;
  }

  @Override
  public long getModificationStamp() {
    // TODO: check if we need to override this method.
    // We relay on file modification instead of content.
    return getVirtualFile().getModificationStamp();
  }

  @Override
  public boolean supportsIncrementalReparse(@NotNull Language rootLanguage) {
    return false;
  }

  @Override
  public void rootChanged(@NotNull PsiFile psiFile) {
  }

  @Override
  public void beforeContentsSynchronized() {
  }

  @Override
  public void contentsSynchronized() {
  }

  @Override
  public FileViewProvider clone() {
    throw new IllegalStateException("mps file view provider -> clone()");
  }

  @Nullable
  @Override
  public PsiElement findElementAt(int offset) {
    return null;
  }

  @Nullable
  @Override
  public PsiReference findReferenceAt(int offset) {
    return null;
  }

  @Nullable
  @Override
  public PsiElement findElementAt(int offset, @NotNull Language language) {
    MPSPsiRootNode psiFile = (MPSPsiRootNode) getPsiInner(getBaseLanguage());
    MPSPsiModel psiModel = psiFile.getContainingModel();
    return psiModel.findNodeByPosition(offset);
  }

  @Nullable
  @Override
  public PsiElement findElementAt(int offset, @NotNull Class<? extends Language> lang) {
    return null;
  }

  @Nullable
  @Override
  public PsiReference findReferenceAt(int offsetInElement, @NotNull Language language) {
    return null;
  }

  @NotNull
  @Override
  public SingleRootFileViewProvider createCopy(@NotNull VirtualFile copy) {
    if (!(copy instanceof MPSNodeVirtualFile)) {
      throw new IllegalStateException();
    }
    return new MPSNodeFileViewProvider(getManager(), (MPSNodeVirtualFile) copy);
  }

  @NotNull
  @Override
  public PsiFile getStubBindingRoot() {
    PsiFile psi = getPsi(getBaseLanguage());
    assert psi != null;
    return psi;
  }

  @Nullable
  @Override
  public <T> T getUserData(@NotNull Key<T> key) {
    return null;
  }

  @Override
  public <T> void putUserData(@NotNull Key<T> key, T value) {
  }
}
