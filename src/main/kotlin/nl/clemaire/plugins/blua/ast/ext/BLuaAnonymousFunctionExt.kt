package nl.clemaire.plugins.blua.ast.ext

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import nl.clemaire.plugins.blua.ast.*

interface BLuaAnonymousFunctionExt : PsiElement, Scoped {
    /**
     * Gets the BLuaParams element under this Anonymous Function node.
     */
    fun getParams(): BLuaParams?

    @JvmDefault
    override fun scope(): Map<String, PsiElement> {
        val declared = (getParams()?.scope() ?: mapOf())

        // Declared parameters and function name override previous scope names
        return getPreviousScope() + declared
    }
}
