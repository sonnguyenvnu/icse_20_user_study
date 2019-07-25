package jetbrains.mps.idea.core.refactoring;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.searches.ReferencesSearch;
import jetbrains.mps.ide.findusages.model.SearchResult;
import jetbrains.mps.ide.findusages.model.SearchResults;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.idea.core.psi.impl.MPSPsiNode;
import jetbrains.mps.idea.core.psi.impl.MPSPsiProvider;
import jetbrains.mps.refactoring.framework.IRefactoring;
import jetbrains.mps.refactoring.framework.IRefactoringTarget;
import jetbrains.mps.refactoring.framework.RefactoringContext;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * danilla 6/14/13
 */

// Composite IRefactoring
// (we can't inherit from refactorings we need because their classes are in languages' classloaders
public class RefactoringWrapper implements IRefactoring {

  protected final IRefactoring baseRefactoring;

  public RefactoringWrapper(IRefactoring base) {
    baseRefactoring = base;
  }

  @Override
  public String getUserFriendlyName() {
    return baseRefactoring.getUserFriendlyName();
  }

  @Override
  public Class getOverridenRefactoringClass() {
    return baseRefactoring.getOverridenRefactoringClass();
  }

  @Override
  public IRefactoringTarget getRefactoringTarget() {
    return baseRefactoring.getRefactoringTarget();
  }

  @Override
  public boolean init(RefactoringContext refactoringContext) {
    return baseRefactoring.init(refactoringContext);
  }

  @Override
  public void refactor(RefactoringContext refactoringContext) {
    baseRefactoring.refactor(refactoringContext);
  }

  @Override
  public List<SModel> getModelsToGenerate(RefactoringContext refactoringContext) {
    return baseRefactoring.getModelsToGenerate(refactoringContext);
  }

  @Override
  public void doWhenDone(RefactoringContext refactoringContext) {
    baseRefactoring.doWhenDone(refactoringContext);
  }

  @Override
  public SearchResults getAffectedNodes(RefactoringContext refactoringContext) {
    return baseRefactoring.getAffectedNodes(refactoringContext);
  }

}
