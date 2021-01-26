package nl.clemaire.plugins.blua.refactor

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement
import nl.clemaire.plugins.blua.ast.BLuaVarRefExpr

class BLuaRefactoringSupportProvider : RefactoringSupportProvider() {
    override fun isMemberInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean =
        element is BLuaVarRefExpr
}
