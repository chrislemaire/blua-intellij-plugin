package nl.clemaire.plugins.blua.ast.ext

import com.intellij.psi.PsiElement
import nl.clemaire.plugins.blua.ast.BLuaIds
import nl.clemaire.plugins.blua.ast.getPreviousScope

interface BLuaParamsExt : PsiElement, Scoped {
    /**
     * Gets the BLuaIds element under this Params node.
     */
    fun getIds(): BLuaIds?

    @JvmDefault
    override fun scope(): Map<String, PsiElement> {
        val declared = getIds()?.getIds()?.map { it.text to it }?.toMap() ?: mapOf()

        // Declared parameters and function name override previous scope names
        return getPreviousScope() + declared
    }
}
