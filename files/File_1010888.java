package jetbrains.mps.idea.java.refactoring;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.searches.MethodReferencesSearch;
import com.intellij.psi.search.searches.OverridingMethodsSearch;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.util.MergeQuery;
import com.intellij.util.Processor;
import com.intellij.util.Query;
import jetbrains.mps.findUsages.UsagesList;
import jetbrains.mps.ide.findusages.model.SearchResult;
import jetbrains.mps.ide.findusages.model.SearchResults;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.idea.core.psi.impl.MPSPsiNode;
import jetbrains.mps.idea.core.psi.impl.MPSPsiNodeBase;
import jetbrains.mps.idea.core.psi.impl.MPSPsiProvider;
import jetbrains.mps.idea.core.refactoring.PsiRenameRefactoringWrapper;
import jetbrains.mps.idea.core.refactoring.PsiSearchResult;
import jetbrains.mps.idea.core.refactoring.RefactoringWrapper;
import jetbrains.mps.refactoring.framework.IRefactoring;
import jetbrains.mps.refactoring.framework.RefactoringContext;
import jetbrains.mps.refactoring.framework.RefactoringUtil;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * danilla 6/4/13
 */

public class PsiMethodRenameRefactoringWrapper extends PsiRenameRefactoringWrapper {

  public PsiMethodRenameRefactoringWrapper() {
    super(RefactoringUtil.getRefactoringByClassName("jetbrains.mps.baseLanguage.refactorings.RenameMethod"));
  }

  @Override
  public void refactor(RefactoringContext refactoringContext) {
    // mps refactoring
    baseRefactoring.refactor(refactoringContext);

    // now do the PSI part of refactoring

    Project project = ProjectHelper.toIdeaProject(refactoringContext.getSelectedProject());
    PsiMethod method = (PsiMethod) MPSPsiProvider.getInstance(project).getPsi(refactoringContext.getSelectedNode());

    String newName = (String) refactoringContext.getParameter("newName");

    SearchResults<SNode> usages = (SearchResults<SNode>) refactoringContext.getUsages();

    for (SearchResult<SNode> result : usages.getSearchResults()) {
      if (!(result instanceof PsiSearchResult)) continue;
      PsiReference psiRef = ((PsiSearchResult) result).getReference();
      if (psiRef.getElement() instanceof MPSPsiNode) continue;
      psiRef.handleElementRename(newName);
    }

    // rename overriding methods
    for (PsiMethod m : OverridingMethodsSearch.search(method).findAll()) {
      if (m instanceof MPSPsiNode) continue;
      m.setName(newName);
    }
  }

  @Override
  public SearchResults getAffectedNodes(RefactoringContext refactoringContext) {
    SearchResults<SNode> mpsResults = baseRefactoring.getAffectedNodes(refactoringContext);

    Project project = ProjectHelper.toIdeaProject(refactoringContext.getSelectedProject());
    PsiElement psiTarget = MPSPsiProvider.getInstance(project).getPsi(refactoringContext.getSelectedNode());
    assert psiTarget instanceof PsiMethod;

    PsiMethod method = (PsiMethod) psiTarget;
    Query<PsiReference> query = query(method);
    Collection<PsiReference> psiRefs = query.findAll();

    // size may be bigger than needed, due to MPS usages returned among PSI usages
    List<SearchResult<SNode>> psiResults = new ArrayList<SearchResult<SNode>>(psiRefs.size());
    for (PsiReference ref : psiRefs) {
      PsiElement element = ref.getElement();
      if (element instanceof MPSPsiNode) continue;

      psiResults.add(new PsiSearchResult(ref));
    }

    mpsResults.addAll(new SearchResults<SNode>(new HashSet<SNode>(), psiResults));

    return mpsResults;
  }

  private Query<PsiReference> query(PsiMethod method) {
    Query<PsiReference> exactUsages = MethodReferencesSearch.search(method);
    // todo search scope?
    // include overriding methods' usages
    // todo fix this ugliness
    final Query<PsiReference>[] query = new Query[]{exactUsages};

    OverridingMethodsSearch.search(method).forEach(new Processor<PsiMethod>() {
      @Override
      public boolean process(PsiMethod psiMethod) {
        Query<PsiReference> q = MethodReferencesSearch.search(psiMethod);
        if (query[0] == null) {
          query[0] = q;
        } else {
          query[0] = new MergeQuery<PsiReference>(query[0], q);
        }
        return false;
      }
    });

    return query[0];
  }
}
