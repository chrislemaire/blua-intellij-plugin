package nl.clemaire.plugins.blua.ast.ext

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference

interface Scoped : PsiElement {
    /**
     * Determines the Scope within this element by first calculating the scope objects declared in this element
     * and then combining those with the previously declared scoped elements.
     */
    fun scope(): Map<String, PsiElement>
}
